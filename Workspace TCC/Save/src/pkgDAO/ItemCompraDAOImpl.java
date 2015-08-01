package pkgDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import pkgEntity.Compra;
import pkgEntity.Material;
import pkgEntity.MaterialApoio;
import pkgEntity.Produto;
import pkgUtil.CriaConexao;

public class ItemCompraDAOImpl {
	
	public boolean inserir(Compra compra) throws ClassNotFoundException, SQLException{
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "INSERT INTO ITEM_COMPRA (QUANTIDADE, BRINDE, COMPRA_ID_COMPRA, SKU) " +
					 "VALUES (?, ? , ?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!compra.getListaMateriais().isEmpty()){
			for(Material m: compra.getListaMateriais()){
				ps.setInt(1, m.getQuantidade());
				ps.setBoolean(2, m.isBrinde());
				ps.setInt(3, compra.getId());
				ps.setInt(4, m.getSku());
				if(ps.executeUpdate() > 0){
					retorno = true;
				}
			}
		}
		criaCon.fecharConexao();
		return retorno;
	}
	
	public boolean atualizar(Compra compra) throws ClassNotFoundException, SQLException{
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "UPDATE ITEM_COMPRA SET (QUANTIDADE, BRINDE) " +
					 "VALUES (?, ? , ?, ?)" +
					 "WHERE SKU = ?" +
					 "AND COMPRA_ID_COMPRA = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!compra.getListaMateriais().isEmpty()){
			for(Material m: compra.getListaMateriais()){
				ps.setInt(1, m.getQuantidade());
				ps.setBoolean(2, m.isBrinde());
				ps.setInt(3, m.getSku());
				ps.setInt(4, compra.getId());
				if(ps.executeUpdate() > 0){
					retorno = true;
				}
			}
		}
		criaCon.fecharConexao();
		return retorno;
	}
	
	public LinkedList<Material> pesquisar(Compra compra) throws ClassNotFoundException, SQLException{
		LinkedList<Material> listaMaterial = new LinkedList<Material>();
		LinkedList<Produto> listaProduto= new LinkedList<Produto>();
		LinkedList<MaterialApoio> listaApoio = new LinkedList<MaterialApoio>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (QUANTIDADE, BRINDE, COMPRA_ID_COMPRA, SKU) " +
					 "FROM ITEM_COMPRA " +
					 "WHERE COMPRA_ID_COMPRA = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!compra.equals(null)){
			ps.setInt(1, compra.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Produto p = new Produto();
				MaterialApoio a = new MaterialApoio();
				p.setQuantidade(rs.getInt("QUANTIDADE"));
				p.setBrinde(rs.getBoolean("BRINDE"));
				p.setSku(rs.getInt("SKU"));
				a.setQuantidade(rs.getInt("QUANTIDADE"));
				a.setBrinde(rs.getBoolean("BRINDE"));
				a.setSku(rs.getInt("SKU"));
				listaApoio.add(a);
				listaProduto.add(p);
			}
			for(Material m: resgataProduto(listaProduto)){
				listaMaterial.add(m);
			}
			for(Material m: resgataApoio(listaApoio)){
				listaMaterial.add(m);
			}
		}
		criaCon.fecharConexao();
		return listaMaterial;
	}
	
	private LinkedList<Material> resgataProduto(LinkedList<Produto> lista) throws ClassNotFoundException, SQLException{
		LinkedList<Material> listaMaterial = new LinkedList<Material>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (NOME, FORNECEDOR_ID_FORNECEDOR) " +
					 "FROM PRODUTO " +
					 "WHERE SKU = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!lista.isEmpty()){
			for(Material m: lista){
				ps.setInt(1, m.getSku());
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Produto p = new Produto();
					p.setNome(rs.getString("NOME"));
					p.getFornecedor().setId(rs.getInt("FORNECEDOR_ID_FORNECEDOR"));
					listaMaterial.add(p);
				}
			}
		}
		criaCon.fecharConexao();
		return listaMaterial;
	}
	
	private LinkedList<Material> resgataApoio(LinkedList<MaterialApoio> lista) throws ClassNotFoundException, SQLException{
		LinkedList<Material> listaMaterial = new LinkedList<Material>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (NOME, FORNECEDOR_ID_FORNECEDOR) " +
					 "FROM MATERIAL_APOIO " +
					 "WHERE SKU = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!lista.isEmpty()){
			for(Material m: lista){
				ps.setInt(1, m.getSku());
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					MaterialApoio a = new MaterialApoio();
					a.setNome(rs.getString("NOME"));
					a.getFornecedor().setId(rs.getInt("FORNECEDOR_ID_FORNECEDOR"));
					listaMaterial.add(a);
				}
			}
		}
		criaCon.fecharConexao();
		return listaMaterial;
	}

}
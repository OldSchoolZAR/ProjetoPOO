package pkgDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import pkgEntity.Material;
import pkgEntity.Produto;
import pkgEntity.Venda;
import pkgUtil.CriaConexao;

public class ItemVendaDAOImpl {

	public boolean inserir(Venda venda) throws ClassNotFoundException, SQLException{
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "INSERT INTO ITEM_VENDA (QUANTIDADE, VENDA_ID_VENDA, PRODUTO_SKU) " +
					 "VALUES (?, ? , ?);";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!venda.getListaProdutos().isEmpty()){
			for(Produto p: venda.getListaProdutos()){
				ps.setInt(1, p.getQuantidade());
				ps.setInt(2, venda.getId());
				ps.setInt(3, p.getSku());
				if(ps.executeUpdate() > 0){
					retorno = true;
				}
			}
		}
		criaCon.fecharConexao();
		return retorno;
	}
	
	public boolean atualizar(Venda venda) throws ClassNotFoundException, SQLException{
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "UPDATE ITEM_VENDA SET (QUANTIDADE) " +
					 "VALUES (?)" +
					 "WHERE VENDA_ID_VENDA = ?" +
					 "AND PRODUTO_SKU = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!venda.getListaProdutos().isEmpty()){
			for(Produto p: venda.getListaProdutos()){
				ps.setInt(1, p.getQuantidade());
				ps.setInt(2, venda.getId());
				ps.setInt(3, p.getSku());
				if(ps.executeUpdate() > 0){
					retorno = true;
				}
			}
		}
		criaCon.fecharConexao();
		return retorno;
	}
	
	public LinkedList<Produto> pesquisar(Venda venda) throws ClassNotFoundException, SQLException{
		LinkedList<Produto> listaProduto= new LinkedList<Produto>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (QUANTIDADE, VENDA_ID_VENDA, PRODUTO_SKU) " +
					 "FROM ITEM_VENDA " +
					 "WHERE VENDA_ID_VENDA = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!venda.equals(null)){
			ps.setInt(1, venda.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Produto p = new Produto();
				p.setQuantidade(rs.getInt("QUANTIDADE"));
				p.setSku(rs.getInt("SKU"));
				listaProduto.add(p);
			}
			for(Produto p: resgataProduto(listaProduto)){
				listaProduto.add(p);
			}
		}
		criaCon.fecharConexao();
		return listaProduto;
	}
	
	private LinkedList<Produto> resgataProduto(LinkedList<Produto> lista) throws ClassNotFoundException, SQLException{
		LinkedList<Produto> listaProduto = new LinkedList<Produto>();
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
					listaProduto.add(p);
				}
			}
		}
		criaCon.fecharConexao();
		return listaProduto;
	}

}
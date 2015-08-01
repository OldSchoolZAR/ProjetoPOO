package pkgDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import pkgEntity.MaterialApoio;
import pkgInterface.MApoioDAO;
import pkgUtil.CriaConexao;

public class MApoioDAOImpl implements MApoioDAO {
	
	public MApoioDAOImpl() {
		super();
	}
	
	private CaracteristicaDAOImpl caracDAO;

	@Override
	public boolean inserir(MaterialApoio material) throws ClassNotFoundException, SQLException {
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "INSERT INTO MATERIAL_APOIO (SKU, QUANTIDADE, NOME, PRECO, UNIDADE, FORNECEDOR_ID_FORNECEDOR) " +
					 "VALUES (?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(sql);
		if (!material.equals(null)){
			ps.setInt(1, material.getSku());
			ps.setInt(2, material.getQuantidade());
			ps.setString(3, material.getNome());
			ps.setDouble(4, material.getPreco());
			ps.setInt(5, material.getUnidade());
			ps.setInt(7, material.getFornecedor().getId());
			if(ps.executeUpdate() > 0){
				retorno = true;
			}
			criaCon.fecharConexao();
			caracDAO.inserir(material);
		}
		return retorno;
	}
	
	@Override
	public boolean atualizar(MaterialApoio material) throws ClassNotFoundException, SQLException {
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "UPDATE MATERIAL_APOIO SET (QUANTIDADE, NOME, PRECO, UNIDADE, FORNECEDOR_ID_FORNECEDOR) " +
					 "VALUES (?, ?, ?, ?, ?)" +
					 "WHERE SKU = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if (!material.equals(null)){
			ps.setInt(1, material.getQuantidade());
			ps.setString(2, material.getNome());
			ps.setDouble(3, material.getPreco());
			ps.setInt(4, material.getUnidade());
			ps.setInt(6, material.getFornecedor().getId());
			ps.setInt(7, material.getSku());
			if(ps.executeUpdate() > 0){
				retorno = true;
			}
			criaCon.fecharConexao();
			caracDAO.atualizar(material);
		}
		return retorno;
	}
	
	@Override
	public LinkedList<MaterialApoio> pesquisar(MaterialApoio material) throws ClassNotFoundException, SQLException {
		LinkedList<MaterialApoio> listaApoio = new LinkedList<MaterialApoio>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (SKU, QUANTIDADE, NOME, PRECO, UNIDADE, FORNECEDOR_ID_FORNECEDOR) " +
					 "FROM MATERIAL_APOIO " +
					 "WHERE NOME LIKE ? " +
					 "ORDER BY NOME ASC;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!material.equals(null)){
			ps.setString(1, "%" + material.getNome() + "%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				MaterialApoio apoio = new MaterialApoio();
				apoio.setSku(rs.getInt("SKU"));
				apoio.setQuantidade(rs.getInt("QUANTIDADE"));
				apoio.setNome(rs.getString("NOME"));
				apoio.setPreco(rs.getDouble("PRECO"));
				apoio.setUnidade(rs.getInt("UNIDADE"));
				apoio.getFornecedor().setId(rs.getInt("FORNECEDOR_ID_FORNECEDOR"));
				listaApoio.add(apoio);
			}
		}
		criaCon.fecharConexao();
		for(MaterialApoio apoio: listaApoio){
			apoio.setCaracterisitca(caracDAO.pesquisar(apoio));
		}
		return listaApoio;
	}

	@Override
	public LinkedList<MaterialApoio> pesquisarTodos() throws ClassNotFoundException, SQLException {
		LinkedList<MaterialApoio> listaApoio = new LinkedList<MaterialApoio>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (SKU, QUANTIDADE, NOME, PRECO, UNIDADE, FORNECEDOR_ID_FORNECEDOR) " +
					 "FROM MATERIAL_APOIO " +
					 "ORDER BY NOME ASC;";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			MaterialApoio apoio = new MaterialApoio();
			apoio.setSku(rs.getInt("SKU"));
			apoio.setQuantidade(rs.getInt("QUANTIDADE"));
			apoio.setNome(rs.getString("NOME"));
			apoio.setPreco(rs.getDouble("PRECO"));
			apoio.setUnidade(rs.getInt("UNIDADE"));
			apoio.getFornecedor().setId(rs.getInt("FORNECEDOR_ID_FORNECEDOR"));
			listaApoio.add(apoio);
		}
		criaCon.fecharConexao();
		for(MaterialApoio apoio: listaApoio){
			apoio.setCaracterisitca(caracDAO.pesquisar(apoio));
		}
		return listaApoio;
	}

}
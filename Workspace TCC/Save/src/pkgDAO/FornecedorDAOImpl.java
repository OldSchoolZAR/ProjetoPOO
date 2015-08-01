package pkgDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import pkgEntity.Fornecedor;
import pkgInterface.FornecedorDAO;
import pkgUtil.CriaConexao;

public class FornecedorDAOImpl implements FornecedorDAO {

	public FornecedorDAOImpl() {
		super();
	}
	
	private DescontoDAOImpl descDAO;
	
	@Override
	public boolean inserir(Fornecedor fornecedor) throws ClassNotFoundException, SQLException {
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "INSERT INTO FORNECEDOR (NOME) " +
					 "VALUES (?);";
		PreparedStatement ps = con.prepareStatement(sql);
		if (!fornecedor.equals(null)){
			ps.setString(1, fornecedor.getNome());
			if(ps.executeUpdate() > 0){
				retorno = true;
			}
			criaCon.fecharConexao();
			descDAO.inserir(fornecedor);
		}
		return retorno;
	}
	
	@Override
	public boolean atualizar(Fornecedor fornecedor) throws ClassNotFoundException, SQLException {
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "UPDATE FORNECEDOR SET (NOME) " +
					 "VALUES (?) " +
					 "WHERE ID_FORNECEDOR = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if (!fornecedor.equals(null)){
			ps.setString(1, fornecedor.getNome());
			ps.setInt(2, fornecedor.getId());
			if(ps.executeUpdate() > 0){
				retorno = true;
			}
			criaCon.fecharConexao();
			descDAO.atualizar(fornecedor);
		}
		return retorno;
	}

	@Override
	public LinkedList<Fornecedor> pesquisar(Fornecedor fornecedor) throws ClassNotFoundException, SQLException {
		LinkedList<Fornecedor> listaFornecedor = new LinkedList<Fornecedor>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_FORNECEDOR, NOME) " +
					 "FROM FORNECEDOR " +
					 "WHERE ID_FORNECEDOR = ? " +
					 "ORDER BY NOME ASC;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!fornecedor.equals(null)){
			ps.setInt(1, fornecedor.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Fornecedor f = new Fornecedor();
				f.setId(rs.getInt("ID_ENTREGA"));
				f.setNome(rs.getString("NOME"));
				listaFornecedor.add(f);
			}
		}
		criaCon.fecharConexao();
		for(Fornecedor f: listaFornecedor){
			f.setDesconto(descDAO.pesquisar(f));
		}
		return listaFornecedor;
	}

	@Override
	public LinkedList<Fornecedor> pesquisarTodos() throws ClassNotFoundException, SQLException {
		LinkedList<Fornecedor> listaFornecedor = new LinkedList<Fornecedor>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_FORNECEDOR, NOME) " +
					 "FROM FORNECEDOR " +
					 "ORDER BY NOME ASC;";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Fornecedor f = new Fornecedor();
			f.setId(rs.getInt("ID_ENTREGA"));
			f.setNome(rs.getString("NOME"));
			listaFornecedor.add(f);
		}
		criaCon.fecharConexao();
		for(Fornecedor f: listaFornecedor){
			f.setDesconto(descDAO.pesquisar(f));
		}
		return listaFornecedor;
	}

}
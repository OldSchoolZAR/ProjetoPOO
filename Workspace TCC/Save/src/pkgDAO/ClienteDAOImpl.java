package pkgDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import pkgEntity.Cliente;
import pkgInterface.ClienteDAO;
import pkgUtil.CriaConexao;

public class ClienteDAOImpl implements ClienteDAO {

	public ClienteDAOImpl() {
		super();
	}
	
	@Override
	public boolean inserir(Cliente cliente) throws SQLException, ClassNotFoundException {
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "INSERT INTO CLIENTE (NOME, NASCIMENTO, TELEFONE, CELULAR, EMAIL) " +
					 "VALUES (?, ?, ?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!cliente.equals(null)){
			ps.setString(1, cliente.getNome());
			ps.setDate(2, new java.sql.Date(cliente.getNascimento().getTime()));
			ps.setString(3, cliente.getTelefone());
			ps.setString(4, cliente.getCelular());
			ps.setString(5, cliente.getEmail());
			if(ps.executeUpdate() > 0){
				retorno = true;
			}
		}
		criaCon.fecharConexao();
		return retorno;
	}

	@Override
	public boolean atualizar(Cliente cliente) throws SQLException, ClassNotFoundException {
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "UPDATE CLIENTE SET (NOME, NASCIMENTO, TELEFONE, CELULAR, EMAIL) " +
					 "VALUES (?, ?, ?, ?, ?) " +
					 "WHERE ID_CLIENTE = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!cliente.equals(null)){
			ps.setString(1, cliente.getNome());
			ps.setDate(2, new java.sql.Date(cliente.getNascimento().getTime()));
			ps.setString(3, cliente.getTelefone());
			ps.setString(4, cliente.getCelular());
			ps.setString(5, cliente.getEmail());
			ps.setInt(6, cliente.getId());
			if(ps.executeUpdate() > 0){
				retorno = true;
			}
		}
		criaCon.fecharConexao();
		return retorno;
	}

	@Override
	public LinkedList<Cliente> pesquisar(Cliente cliente) throws SQLException, ClassNotFoundException {
		LinkedList<Cliente> listaCliente = new LinkedList<Cliente>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_CLIENTE, NOME, NASCIMENTO, TELEFONE, CELULAR, EMAIL) " +
					 "FROM CLIENTE " +
					 "WHERE NOME LIKE ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!cliente.equals(null)){
			ps.setString(1, "%" + cliente.getNome() + "%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Cliente cli = new Cliente();
				cli.setId(rs.getInt("ID_CLIENTE"));
				cli.setNome(rs.getString("NOME"));
				cli.setNascimento(new java.util.Date(rs.getDate("NASCIMENTO").getTime()));
				cli.setTelefone(rs.getString("TELEFONE"));
				cli.setCelular(rs.getString("CELULAR"));
				cli.setEmail(rs.getString("EMAIL"));
				listaCliente.add(cli);
			}
		}
		criaCon.fecharConexao();
		return listaCliente;
	}
	
	@Override
	public Cliente pesquisar(int idCliente) throws SQLException, ClassNotFoundException {
		Cliente cli = new Cliente();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_CLIENTE, NOME, NASCIMENTO, TELEFONE, CELULAR, EMAIL) " +
					 "FROM CLIENTE " +
					 "WHERE ID_CLIENTE = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!(idCliente != 0)){
			ps.setInt(1, idCliente);
			ResultSet rs = ps.executeQuery();
			cli.setId(rs.getInt("ID_CLIENTE"));
			cli.setNome(rs.getString("NOME"));
			cli.setNascimento(new java.util.Date(rs.getDate("NASCIMENTO").getTime()));
			cli.setTelefone(rs.getString("TELEFONE"));
			cli.setCelular(rs.getString("CELULAR"));
			cli.setEmail(rs.getString("EMAIL"));
		}
		criaCon.fecharConexao();
		return cli;
	}
	
	@Override
	public LinkedList<Cliente> pesquisarTodos() throws SQLException, ClassNotFoundException {
		LinkedList<Cliente> listaCliente = new LinkedList<Cliente>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_CLIENTE, NOME, NASCIMENTO, TELEFONE, CELULAR, EMAIL) " +
					 "FROM CLIENTE;";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Cliente cli = new Cliente();
			cli.setId(rs.getInt("ID_CLIENTE"));
			cli.setNome(rs.getString("NOME"));
			cli.setNascimento(new java.util.Date(rs.getDate("NASCIMENTO").getTime()));
			cli.setTelefone(rs.getString("TELEFONE"));
			cli.setCelular(rs.getString("CELULAR"));
			cli.setEmail(rs.getString("EMAIL"));
			listaCliente.add(cli);
		}
		criaCon.fecharConexao();
		return listaCliente;
	}

}
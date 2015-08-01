package pkgInterface;

import java.sql.SQLException;
import java.util.LinkedList;

import pkgEntity.Cliente;

public interface ClienteDAO {

	public boolean inserir(Cliente cliente) throws SQLException, ClassNotFoundException;
	
	public boolean atualizar(Cliente cliente) throws SQLException, ClassNotFoundException;

	public LinkedList<Cliente> pesquisar(Cliente cliente) throws SQLException, ClassNotFoundException;

	public Cliente pesquisar(int idCliente) throws SQLException,
			ClassNotFoundException;
	
	public LinkedList<Cliente> pesquisarTodos() throws SQLException, ClassNotFoundException;

}
package pkgInterface;

import java.sql.SQLException;
import java.util.LinkedList;

import pkgEntity.Fornecedor;

public interface FornecedorDAO {

	public boolean atualizar(Fornecedor fornecedor) throws ClassNotFoundException, SQLException;

	public boolean inserir(Fornecedor fornecedor) throws ClassNotFoundException, SQLException;

	public LinkedList<Fornecedor> pesquisar(Fornecedor fornecedor) throws ClassNotFoundException, SQLException;

	public LinkedList<Fornecedor> pesquisarTodos() throws ClassNotFoundException, SQLException;

}
package pkgInterface;

import java.sql.SQLException;
import java.util.LinkedList;

import pkgEntity.Produto;

public interface ProdutoDAO {

	public boolean atualizar(Produto produto) throws ClassNotFoundException, SQLException;

	public boolean inserir(Produto produto) throws ClassNotFoundException, SQLException;

	public LinkedList<Produto> pesquisar(Produto produto) throws ClassNotFoundException, SQLException;

	public LinkedList<Produto> pesquisarTodos() throws ClassNotFoundException, SQLException;

}
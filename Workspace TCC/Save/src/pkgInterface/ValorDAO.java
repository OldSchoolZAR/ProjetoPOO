package pkgInterface;

import java.sql.SQLException;
import java.util.LinkedList;

import pkgEntity.Produto;
import pkgEntity.Valor;

public interface ValorDAO {

	public boolean atualizar(Produto produto) throws ClassNotFoundException, SQLException;

	public boolean inserir(Produto produto) throws ClassNotFoundException, SQLException;

	public LinkedList<Valor> pesquisarValor(Produto produto) throws ClassNotFoundException, SQLException;

}
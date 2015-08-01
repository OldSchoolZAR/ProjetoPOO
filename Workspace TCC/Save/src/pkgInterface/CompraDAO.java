package pkgInterface;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import pkgEntity.Compra;

public interface CompraDAO {

	public boolean atualizar(Compra compra) throws ClassNotFoundException, SQLException;

	public boolean inserir(Compra compra) throws ClassNotFoundException, SQLException;

	public LinkedList<Compra> pesquisar(Compra compra) throws ClassNotFoundException, SQLException;
	
	public LinkedList<Compra> pesquisar(Date dataInicio, Date dataFim) throws ClassNotFoundException, SQLException;

	public LinkedList<Compra> pesquisarTodos() throws ClassNotFoundException, SQLException;

}
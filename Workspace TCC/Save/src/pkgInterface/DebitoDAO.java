package pkgInterface;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import pkgEntity.Compra;
import pkgEntity.Debito;

public interface DebitoDAO {

	public boolean atualizar(Compra compra) throws ClassNotFoundException, SQLException;

	public boolean inserir(Compra compra) throws ClassNotFoundException, SQLException;

	public LinkedList<Debito> pesquisar(Compra compra) throws ClassNotFoundException, SQLException;
	
	public LinkedList<Debito> pesquisar(Date dataInicio, Date dataFim) throws ClassNotFoundException, SQLException;
	
	public LinkedList<Debito> pesquisarTodos() throws ClassNotFoundException, SQLException;

}
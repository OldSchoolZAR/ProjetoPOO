package pkgInterface;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import pkgEntity.Venda;

public interface VendaDAO {

	public boolean atualizar(Venda venda) throws ClassNotFoundException, SQLException;

	public boolean inserir(Venda venda) throws ClassNotFoundException, SQLException;

	public LinkedList<Venda> pesquisar(Venda venda) throws ClassNotFoundException, SQLException;
	
	public LinkedList<Venda> pesquisar(Date dataInicio, Date dataFim) throws ClassNotFoundException, SQLException;

	public LinkedList<Venda> pesquisarTodos() throws ClassNotFoundException, SQLException;

}
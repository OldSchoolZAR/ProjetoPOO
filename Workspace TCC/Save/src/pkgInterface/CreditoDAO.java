package pkgInterface;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import pkgEntity.Credito;
import pkgEntity.Venda;

public interface CreditoDAO {

	public boolean atualizar(Venda venda) throws SQLException, ClassNotFoundException;

	public boolean inserir(Venda venda) throws SQLException, ClassNotFoundException;

	public LinkedList<Credito> pesquisar(Venda venda) throws SQLException, ClassNotFoundException;
	
	public LinkedList<Credito> pesquisar(Date dataInicio, Date dataFim) throws SQLException, ClassNotFoundException;
	
	public LinkedList<Credito> pesquisarTodos() throws ClassNotFoundException, SQLException;

}
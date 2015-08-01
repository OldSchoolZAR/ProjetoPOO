package pkgInterface;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import pkgEntity.Entrega;
import pkgEntity.Venda;

public interface EntregaDAO {

	public boolean atualizar(Venda venda) throws ClassNotFoundException, SQLException;

	public boolean inserir(Venda venda) throws ClassNotFoundException, SQLException;

	public LinkedList<Entrega> pesquisar(Venda venda) throws ClassNotFoundException, SQLException;
	
	public LinkedList<Entrega> pesquisar(Date dataInicio, Date dataFim) throws ClassNotFoundException, SQLException;

	public LinkedList<Entrega> pesquisarTodos() throws ClassNotFoundException, SQLException;

}
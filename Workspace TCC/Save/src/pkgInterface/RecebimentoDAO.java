package pkgInterface;

import java.sql.SQLException;
import java.util.LinkedList;

import pkgEntity.Compra;
import pkgEntity.Recebimento;

public interface RecebimentoDAO {

	public boolean inserir(Compra compra) throws ClassNotFoundException, SQLException;
	
	public boolean atualizar(Compra compra) throws ClassNotFoundException, SQLException;
	
	public LinkedList<Recebimento> pesquisar(Compra compra) throws ClassNotFoundException, SQLException;

}
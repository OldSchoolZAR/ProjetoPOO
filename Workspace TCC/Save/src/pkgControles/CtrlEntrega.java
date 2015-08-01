package pkgControles;

import java.sql.SQLException;
import java.util.LinkedList;

import pkgDAO.EntregaDAOImpl;
import pkgEntity.Entrega;
import pkgEntity.Venda;
import pkgInterface.EntregaDAO;

public class CtrlEntrega {

	private EntregaDAO entregaDAO;


	public CtrlEntrega() {
		entregaDAO = new EntregaDAOImpl();
	}

	public boolean inserir(Venda venda) throws ClassNotFoundException,
			SQLException {
		if(venda != null){
			return entregaDAO.inserir(venda);
		}
		return false;
	}

	public boolean atualizar(Venda venda) throws ClassNotFoundException,
			SQLException {
		if (venda != null) {
			return entregaDAO.atualizar(venda);
		}
		return false;
	}
	
	public LinkedList<Entrega> pesquisar(Venda venda)
			throws ClassNotFoundException, SQLException {
		LinkedList<Entrega> listaEntrega = null;
		if (venda != null) {
			listaEntrega = entregaDAO.pesquisar(venda);
		}
		return listaEntrega;
	}
}

package pkgControles;

import java.sql.SQLException;
import java.util.LinkedList;

import pkgDAO.CreditoDAOImpl;
import pkgEntity.Credito;
import pkgEntity.Venda;
import pkgInterface.CreditoDAO;

public class CtrlCredito {
	private CreditoDAO creditoDAO;

	public CtrlCredito() {
		creditoDAO = new CreditoDAOImpl();
	}

	public boolean inserir(Venda venda) throws ClassNotFoundException, SQLException {
		if (venda != null){
			return creditoDAO.inserir(venda);
		}
		else{
			return false;
		}
	}
	public boolean atualizar(Venda venda) throws ClassNotFoundException, SQLException {
		if (venda != null) {
			return creditoDAO.atualizar(venda);
		}
		else{
			return false;
		}
	}

	public LinkedList<Credito> pesquisar(Venda venda)throws ClassNotFoundException, SQLException {
		LinkedList<Credito> listaCredito = null;
		if (venda != null) {
			listaCredito = creditoDAO.pesquisar(venda);
		}
		return listaCredito;
	}
}

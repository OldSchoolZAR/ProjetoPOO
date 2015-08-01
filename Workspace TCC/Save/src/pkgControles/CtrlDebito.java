package pkgControles;

import java.sql.SQLException;
import java.util.LinkedList;

import pkgDAO.DebitoDAOImpl;
import pkgEntity.Compra;
import pkgEntity.Debito;
import pkgInterface.DebitoDAO;

public class CtrlDebito {

	private DebitoDAO debitoDAO;
	
	public CtrlDebito() {
		debitoDAO = new DebitoDAOImpl();
	}

	public boolean inserir(Compra compra) throws ClassNotFoundException, SQLException {
		if (compra != null && validaDebito (compra)){
			return debitoDAO.inserir(compra);
		} else{
			return false;
		}
	}

	public boolean atualizar(Compra compra) throws ClassNotFoundException, SQLException {
		if (compra != null && validaDebito (compra)) {
			return debitoDAO.atualizar(compra);
		}
		else{
			return false;
		}
	}

	public LinkedList<Debito> pesquisar(Compra compra) throws ClassNotFoundException, SQLException {
		LinkedList<Debito> listaDebito = null;
		if (compra != null) {
			listaDebito = debitoDAO.pesquisar(compra);
		}
		return listaDebito;
	}
	
	private  boolean validaDebito (Compra compra){
		LinkedList<Debito> lstdebito = new LinkedList<Debito>();
		boolean boo = true;
		lstdebito = compra.getDebito();
		for(Debito d : lstdebito){
			if (!compra.getData().before(d.getVencimento())){
				boo = false;
			}
		}
		return boo;
	}	
}

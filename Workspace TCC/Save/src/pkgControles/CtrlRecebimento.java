package pkgControles;

import java.sql.SQLException;
import java.util.LinkedList;

import pkgDAO.RecebimentoDAOImpl;
import pkgEntity.Compra;
import pkgEntity.Recebimento;
import pkgInterface.RecebimentoDAO;

public class CtrlRecebimento {
	private RecebimentoDAO recebimentoDAO;

	public CtrlRecebimento() {
		recebimentoDAO = new RecebimentoDAOImpl();
	}

	public boolean inserir(Compra compra) throws ClassNotFoundException,
			SQLException {
		if (validaRecebimento(compra)) {
			return recebimentoDAO.inserir(compra);
		} else {
			return false;
		}

	}

	public boolean atualizar(Compra compra) throws ClassNotFoundException,
			SQLException {

		if (validaRecebimento(compra)) {
			return recebimentoDAO.atualizar(compra);
		} else {
			return false;
		}
	}

	public LinkedList<Recebimento> pesquisar(Compra compra)
			throws ClassNotFoundException, SQLException {
		LinkedList<Recebimento> listaRecebimento = new LinkedList<Recebimento>();
		if (compra != null) {
			listaRecebimento = recebimentoDAO.pesquisar(compra);
		}
		return listaRecebimento;
	}

	private boolean validaRecebimento(Compra compra) {
		boolean boo = true;
		LinkedList<Recebimento> lstrecebimento = new LinkedList<Recebimento>();
		lstrecebimento = compra.getRecebimento();
		for (Recebimento r : lstrecebimento) {
			if (r != null) {
				if (compra.getData().compareTo(r.getPrazoEntrega()) <= 0) {
					boo = false;
				}
				if (!compra.getData().before(r.getDataEntrega())) {
					boo = false;
				}
				if (r.getPrazoEntrega() == null
						|| r.getPrazoEntrega().before(compra.getData())) {
					boo = false;
				}
			} else {
				boo = false;
			}
		}
		return boo;
	}

}

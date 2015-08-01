package pkgControles;

import java.sql.SQLException;
import java.util.LinkedList;

import pkgDAO.MApoioDAOImpl;
import pkgEntity.MaterialApoio;
import pkgInterface.MApoioDAO;

public class CtrlMApoio {

	private MApoioDAO materialDAO;

	public CtrlMApoio() {
		materialDAO = new MApoioDAOImpl();
	}

	public boolean inserir(MaterialApoio mApoio) throws ClassNotFoundException,
			SQLException {
		if (mApoio != null) {
			return materialDAO.inserir(mApoio);
		} else {
			return false;
		}
	}

	public boolean atualizar(MaterialApoio mApoio)
			throws ClassNotFoundException, SQLException {
		if (mApoio != null) {
			return materialDAO.atualizar(mApoio);
		} else {
			return false;
		}
	}

	public LinkedList<MaterialApoio> pesquisar(MaterialApoio mApoio)
			throws ClassNotFoundException, SQLException {
		LinkedList<MaterialApoio> listaMApoio = null;
		if (mApoio != null) {
			listaMApoio = materialDAO.pesquisar(mApoio);
		}
		return listaMApoio;
	}
	public LinkedList<MaterialApoio> pesquisarTodos()
			throws ClassNotFoundException, SQLException {
		LinkedList<MaterialApoio> listaMApoio = null;
		listaMApoio = materialDAO.pesquisarTodos();
		return listaMApoio;
	}
	public MApoioDAO getMaterialDAO() {
		return materialDAO;
	}

	public void setMaterialDAO(MApoioDAO materialDAO) {
		this.materialDAO = materialDAO;
	}

}

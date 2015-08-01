package pkgControles;

import java.sql.SQLException;
import java.util.LinkedList;

import pkgDAO.ValorDAOImpl;
import pkgEntity.Produto;
import pkgEntity.Valor;
import pkgInterface.ValorDAO;

public class CtrlValor {

	private ValorDAO valorDAO;

	public CtrlValor() {
		valorDAO = new ValorDAOImpl();
	}

	public boolean inserir(Produto produto) throws ClassNotFoundException, SQLException {
		if (produto != null) {
			return valorDAO.inserir(produto);
		} else {
			return false;
		}
	}

	public boolean atualizar(Produto produto) throws ClassNotFoundException,
			SQLException {
		if (produto != null) {
			return valorDAO.atualizar(produto);
		} else {
			return false;
		}
	}
	
	public LinkedList<Valor> pesquisar(Produto produto)
			throws ClassNotFoundException, SQLException {
		LinkedList<Valor> listaValores = null;
		if (produto != null) {
			listaValores = valorDAO.pesquisarValor(produto);
		}
		return listaValores;
	}
}

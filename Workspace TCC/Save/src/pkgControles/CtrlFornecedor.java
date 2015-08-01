package pkgControles;

import java.sql.SQLException;
import java.util.LinkedList;

import pkgDAO.FornecedorDAOImpl;
import pkgEntity.Fornecedor;
import pkgInterface.FornecedorDAO;

public class CtrlFornecedor {

	private FornecedorDAO forneceDAO;

	public CtrlFornecedor() {
		forneceDAO = new FornecedorDAOImpl();
	}
	
	
	public boolean inserir(Fornecedor fornecedor) throws ClassNotFoundException, SQLException {
		if(fornecedor != null){
			return forneceDAO.inserir(fornecedor);
		}else{
			return false;
		}
	}
	public boolean atualizar(Fornecedor fornecedor) throws ClassNotFoundException, SQLException {
		if (fornecedor != null) {
			return forneceDAO.atualizar(fornecedor);
		}else{
			return false;
		}
	}
	public LinkedList<Fornecedor> pesquisar(Fornecedor fornecedor) throws ClassNotFoundException, SQLException {
		LinkedList<Fornecedor> listaFornecedor = null;
		if (fornecedor != null) {
			listaFornecedor = forneceDAO.pesquisar(fornecedor);
		}
		return listaFornecedor;
	}
}

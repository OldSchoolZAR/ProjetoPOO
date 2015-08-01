package pkgControles;

import java.sql.SQLException;
import java.util.LinkedList;

import pkgDAO.ProdutoDAOImpl;
import pkgEntity.Produto;
import pkgInterface.ProdutoDAO;

public class CtrlProduto {


	private ProdutoDAO produtoDAO;

	public CtrlProduto() {
		produtoDAO = new ProdutoDAOImpl();
	}

	public boolean inserir(Produto produto) throws ClassNotFoundException,
			SQLException {
		if (produto != null) {
			return produtoDAO.inserir(produto);
		} else {
			return false;
		}
	}

	public boolean atualizar(Produto produto) throws ClassNotFoundException,
			SQLException {
		if (produto != null) {
			return produtoDAO.atualizar(produto);
		} else {
			return false;
		}
	}

	public LinkedList<Produto> pesquisar(Produto produto)
			throws ClassNotFoundException, SQLException {
		LinkedList<Produto> listaProdutos = null;
		if (produto != null) {
			listaProdutos = produtoDAO.pesquisar(produto);
		}
		return listaProdutos;
	}
	
	public LinkedList<Produto> pesquisarTodos() throws ClassNotFoundException, SQLException{
		LinkedList<Produto> retorno = new LinkedList<Produto>();
		retorno = produtoDAO.pesquisarTodos();
		if (retorno.size() <1){
			Produto p = new Produto();
			p.setNome("Não ha produtos ou ocorreu um erro");
		}
		return retorno;
	}
	
	
	
	
	
}
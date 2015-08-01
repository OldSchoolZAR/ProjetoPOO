package pkgControles;

import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;

import pkgDAO.VendaDAOImpl;
import pkgEntity.Compra;
import pkgEntity.Material;
import pkgEntity.Produto;
import pkgEntity.Venda;
import pkgInterface.ICompraVenda;
import pkgInterface.VendaDAO;

public class CtrlVenda implements ICompraVenda {

	private Venda actVenda;
	private VendaDAO vendaDAO;
	private CtrlCompra ctrlCompra;
	private CtrlCredito ctrlCredito;
	private CtrlEntrega ctrlEntrega;
	
	public CtrlVenda() {
		actVenda = new Venda();
		ctrlCompra = new CtrlCompra();
		ctrlCredito = new CtrlCredito();
		ctrlEntrega = new CtrlEntrega();
		vendaDAO = new VendaDAOImpl();
	}

	public Venda getActVenda() {
		return actVenda;
	}

	public void setActVenda(Venda venda) {
		this.actVenda = venda;
	}

	public double obterLucro(Venda venda) throws ClassNotFoundException, SQLException {

		LinkedList<Produto> listaVenda = venda.getListaProdutos();
		LinkedList<Produto> noStock = new LinkedList<Produto>();
		LinkedList<Compra> comprasValidas = new LinkedList<Compra>();

		comprasValidas = pesquisaCompra(listaVenda);
		noStock = pesquisaProduto(comprasValidas, listaVenda);

		for (Produto p : listaVenda) {
			if (noStock.contains(p)) {
				listaVenda.remove(p);
			}
		}
		double auxLucro = 0;
		LinkedList<Double> descontoMedio = new LinkedList<Double>();
		calculaDescontoMedio(descontoMedio);
		
		LoopProduto : for (Produto p : listaVenda) {
			int unidadesVendidas = p.getQuantidade();

			LoopCompra : for (Compra c : comprasValidas) {
				for (Material m : c.getListaMateriais()) {
					
					if (p.getSku() == m.getSku()) {
						Produto pd = (Produto) m;
						int unidadesCompradas = pd.getQuantidade();

						if (unidadesCompradas >= unidadesVendidas) {
							unidadesCompradas -= unidadesVendidas;
							
							auxLucro += unidadesVendidas * (p.getValor().getPrecoSugerido() * c.getDesconto());
							descontoMedio.add( c.getDesconto());
							unidadesVendidas = 0;
							break LoopProduto;
						} 
						else {
							unidadesVendidas -= unidadesCompradas;
							unidadesCompradas = 0;
							
							auxLucro += unidadesVendidas * (p.getValor().getPrecoSugerido() * c.getDesconto());
							descontoMedio.add( c.getDesconto());
							break LoopCompra ;
						}
					}
				}
			}
		}
		return auxLucro;
	}
	
	private double calculaDescontoMedio(LinkedList<Double> descontoMedio) {
		double ret = 0;
		double media = 0;
		for (Double d : descontoMedio){
			ret += d;
		}
		media = ret / (descontoMedio.size()-1);
		
		return media;
		// TODO Auto-generated method stub
		
	}

	public double calculaValorVenda(Venda venda){
		double valorVenda = 0;
		for (Produto p : venda.getListaProdutos())		
			valorVenda += p.getQuantidade() * p.getValor().getPrecoSugerido();
		return valorVenda;
	}
	
	public int calculaPontoPorVenda(Venda venda){
		int pontos = 0;
		for (Produto p : venda.getListaProdutos())		
			pontos += p.getQuantidade() * p.getValor().getQtdPonto();
		return pontos;
	}
	
	public boolean inserir() throws ClassNotFoundException, SQLException {
		if (actVenda != null){
			actVenda.setLucro( obterLucro(actVenda) );
			actVenda.setValorVenda(calculaPontoPorVenda(actVenda));
			return vendaDAO.inserir(actVenda);
		}
		return false;
	}

	public boolean atualizar(Venda venda) throws ClassNotFoundException, SQLException {
		if (venda != null) {
			if (vendaDAO.atualizar(venda)) {
				if ( atualizarCredito(venda) && atualizarEntrega(venda) ) {
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	private boolean atualizarEntrega(Venda venda) throws ClassNotFoundException, SQLException {
		LinkedList<Venda> lstvenda = vendaDAO.pesquisar(venda);
		Venda vndOriginal;
		Venda vndModificada = new Venda();
		boolean checkA;
		boolean checkB;

		if( lstvenda != null ){
				for (Venda t : lstvenda){
					if (t.getId() == venda.getId()){
						vndOriginal = t;
						vndModificada.setId(vndOriginal.getId());
						if (venda.entrega.size() >= vndOriginal.entrega.size() ){
							vndModificada.entrega = null;
							vndModificada.entrega.add(venda.entrega.getLast());
							checkA = ctrlEntrega.inserir(vndModificada);
							checkB = ctrlEntrega.atualizar(venda);
							if(checkA && checkB){
								return true;
							}else{
								return false;
							}
						}else{
							return ctrlEntrega.atualizar(venda);
						}	
					}
				}
				return false;
		}else{
			return false;
		}
	}

	private boolean atualizarCredito(Venda venda) throws ClassNotFoundException, SQLException {
		LinkedList<Venda> lstvenda = vendaDAO.pesquisar(venda);
		Venda vndOriginal;
		Venda vndModificada = new Venda();
		boolean checkA;
		boolean checkB;

		if( lstvenda != null ){
				for (Venda t : lstvenda){
					if (t.getId() == venda.getId()){
						vndOriginal = t;
						vndModificada.setId(vndOriginal.getId());
						if (venda.credito.size() >= vndOriginal.credito.size() ){
							vndModificada.credito = null;
							vndModificada.credito.add(venda.credito.getLast());
							checkA = ctrlCredito.inserir(vndModificada);
							checkB = ctrlCredito.atualizar(venda);
							if(checkA && checkB){
								return true;
							}else{
								return false;
							}
						}else{
							return ctrlCredito.atualizar(venda);
						}	
					}
				}
				return false;
		}else{
			return false;
		}
	}

	public LinkedList<Venda> pesquisar(Venda venda) throws ClassNotFoundException, SQLException {
		LinkedList<Venda> listaVenda = null;
		if (actVenda != null) {
			listaVenda = vendaDAO.pesquisar(actVenda);
		}
		return listaVenda;
	}

	public LinkedList<Venda> pesquisaTodos() throws ClassNotFoundException, SQLException {
		LinkedList<Venda> listaVenda = null;
		if (actVenda != null) {
			listaVenda = vendaDAO.pesquisar(actVenda);
		}
		return listaVenda;
	}

	@Override
	public void remover(Material material) {
		// TODO implement me
	}

	@Override
	public void adicionar(Material material) {
		// TODO implement me
	}

	private LinkedList<Compra> ReverseLinkedList(LinkedList<Compra> linkedList) {
		Collections.reverse(linkedList);
		return linkedList;
	}

	private LinkedList<Compra> pesquisaCompra(LinkedList<Produto> lProduto)
			throws ClassNotFoundException, SQLException {
		LinkedList<Compra> listaCompra = ctrlCompra.pesquisarTodos();
		LinkedList<Compra> retorno = new LinkedList<Compra>();
		if (listaCompra != null) {
			ReverseLinkedList(listaCompra);
			for (Compra c : listaCompra) {
				for (Produto p : lProduto) {
					if (c.getListaMateriais().contains(p)) {
						if (!retorno.contains(c)) {
							retorno.add(c);
						}
					}
				}
			}
		}
		return retorno;
	}

	private LinkedList<Produto> pesquisaProduto(LinkedList<Compra> listaCompra,
			LinkedList<Produto> lProduto) throws ClassNotFoundException,
			SQLException {
		LinkedList<Produto> retorno = new LinkedList<Produto>();
		if (listaCompra != null) {
			for (Produto p : lProduto) {
				int count = 0;
				for (Compra c : listaCompra) {
					if (!c.getListaMateriais().contains(p)) {
						count++;
					}
				}
				if (count >= (listaCompra.size() - 1)) {
					retorno.add(p);
				}
			}
		}
		return retorno;
	}
}
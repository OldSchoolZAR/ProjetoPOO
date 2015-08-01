package pkgControles;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import pkgDAO.CompraDAOImpl;
import pkgEntity.Compra;
import pkgEntity.Desconto;
import pkgEntity.Fornecedor;
import pkgEntity.Material;
import pkgEntity.MaterialApoio;
import pkgEntity.Produto;
import pkgEntity.Valor;
import pkgInterface.CompraDAO;
import pkgInterface.ICompraVenda;

// Dennis 
public class CtrlCompra implements ICompraVenda {
	
	private Compra actCompra;
	private CompraDAO compraDAO;
	private double estoqueMaterialApoio;
	private CtrlDebito ctrlDebito;
	private CtrlRecebimento ctrlRecebimento;
	
	public CtrlCompra() {
		actCompra = new Compra();
		estoqueMaterialApoio =0.00;
		ctrlDebito = new CtrlDebito();
		ctrlRecebimento = new CtrlRecebimento();
		compraDAO = new CompraDAOImpl();
	}

	public Compra getActCompra() {
		return actCompra;
	}
	
	public void setActCompra(Compra actCompra) {
		this.actCompra = actCompra;
	}
	
	public void obterValorEstoque(){
		MaterialApoio ma;
		Produto pd;
		Double auxValor =  0.00;

		for( Material m : actCompra.getListaMateriais()){
			int qntBrinde = 0;
			estoqueMaterialApoio = 0.00;

			if(m instanceof MaterialApoio){
				ma = (MaterialApoio) m;
				ma.getQuantidade();
				estoqueMaterialApoio += (ma.getPreco() * ma.getQuantidade());
			}
			if(m instanceof Produto){
				pd = (Produto) m;
				if(pd.isBrinde()){
					qntBrinde = pd.getQuantidadeBrinde();
				}
				Valor v = pd.valor.getFirst();
				auxValor += (v.getPrecoSugerido() * (pd.getQuantidade() - qntBrinde ));
			}
		}
		actCompra.setValorEstoque(auxValor);
	}

	public void obterDesconto() throws ClassNotFoundException, SQLException, ParseException {
		Date aux = null;
		Fornecedor forn = getFornecedorDaCompra();
		double descontoMinimo = 0;
		
		switch (forn.getNome()){
			case "Mary Kay" : 
				descontoMinimo = obterDescontoMinimo (actCompra); 
				break;	
			case "Natura" : 
				descontoMinimo = 0.3; 
				break;
		}
		for(Desconto d : forn.getDesconto()){
			if (aux != null){
				if(d.getData().compareTo(aux) != 0){
					if (  descontoMinimo > 0  ){
						actCompra.setDesconto(descontoMinimo);
					}
					else{
						actCompra.setDesconto(0);
					}
				}
				else if (validaValorDesconto(actCompra.getValorEstoque(), d.getValorDesconto())){
					actCompra.setDesconto(d.getDesconto());
				}
			}
			else{
				if (d.getData().before(actCompra.getData())){ 
					if (validaValorDesconto(actCompra.getValorEstoque(), d.getValorDesconto())){
						actCompra.setDesconto(d.getDesconto());
					}
					else{
						aux = d.getData();
					}
				}
			}
		}
	}
	
	public int calculaPontoPorCompra(Compra compra){
		int pontos = 0;
		for (Material m : compra.getListaMateriais()){
			if (m instanceof Produto){
				Produto p = (Produto) m;
				pontos += p.getQuantidade() * p.getValor().getQtdPonto();
			}
		}
		return pontos;
	}
	
	public boolean inserir() throws ClassNotFoundException, SQLException, ParseException {
		if(actCompra != null){
			obterValorEstoque();
			obterDesconto();
			return compraDAO.inserir(getActCompra());
		} else{
			return false;
		}
	}
	
	public boolean atualizar(Compra compra) throws ClassNotFoundException, SQLException {
		if (actCompra != null) {
			atualizaDebito(actCompra);
			atualizaRecebimento(actCompra);
			return compraDAO.atualizar(actCompra);
		}
		else{
			return false;
		}
	}
	
	public LinkedList<Compra> pesquisar(Compra compra) throws ClassNotFoundException, SQLException {
		LinkedList<Compra> listaCompra = new LinkedList<Compra>();
		try{
			if (compra != null) {
				for (Compra c : compraDAO.pesquisar(compra)){
					c.debito = ctrlDebito.pesquisar(c);
					c.recebimento = ctrlRecebimento.pesquisar(c);
					listaCompra.add(c);
				}
			}
		return listaCompra; 
		}
		catch(ClassNotFoundException | SQLException e ){
			throw e;
		}
	}
	
	public LinkedList<Compra> pesquisarTodos() throws ClassNotFoundException, SQLException{
		LinkedList<Compra> listaCompra = new LinkedList<Compra>();
		try{
			for (Compra c : compraDAO.pesquisarTodos()){
				c.debito = ctrlDebito.pesquisar(c);
				c.recebimento = ctrlRecebimento.pesquisar(c);
				listaCompra.add(c);
			}
			return listaCompra;
		}
		catch(ClassNotFoundException | SQLException e ){
			throw e;
		}
	}
	
	@Override
	public void adicionar(Material material) {
		actCompra.setListaMateriais(material);
	}
	
	@Override
	public void remover(Material material) {
		actCompra.removeListaMateriais(material);
	}
		
	public boolean atualizaDebito(Compra compra) throws ClassNotFoundException, SQLException{
		LinkedList<Compra> lstcompra = compraDAO.pesquisar(compra);
		Compra cmpOriginal;
		Compra cmpModificada = new Compra();
		
		if( lstcompra != null ){
			for (Compra t : lstcompra){
				if (t.getId() == compra.getId()){
					cmpOriginal = t;
					cmpModificada.setId(cmpOriginal.getId());
					if (compra.debito.size() >= cmpOriginal.debito.size() ){
						cmpModificada.debito = null;
						cmpModificada.debito.add(compra.debito.getLast());
						ctrlDebito.inserir(cmpModificada);
					}
					return ctrlDebito.atualizar(compra);
				}
			}
		}
		return false;		
	}

	public boolean atualizaRecebimento(Compra compra) throws ClassNotFoundException, SQLException{
		LinkedList<Compra> lstcompra = compraDAO.pesquisar(compra);
		Compra cmpOriginal;
		Compra cmpModificada = new Compra();
		
		if( lstcompra != null ){
			for (Compra t : lstcompra){
				if (t.getId() == compra.getId()){
					cmpOriginal = t;
					cmpModificada.setId(cmpOriginal.getId());
					if (compra.recebimento.size() >= cmpOriginal.recebimento.size() ){
						cmpModificada.recebimento = null;
						cmpModificada.recebimento.add(compra.recebimento.getLast());
						ctrlRecebimento.inserir(cmpModificada);
					}
					return ctrlRecebimento.atualizar(compra);
				}
			}
		}
		return false;
	}
			
	private LinkedList<Compra> pesquisar(Date dataInicio, Date dataFim) throws ClassNotFoundException, SQLException {
		LinkedList<Compra> listaCompra = new LinkedList<Compra>();
		try{
			for (Compra c : compraDAO.pesquisar(dataInicio, dataFim)){
				c.debito = ctrlDebito.pesquisar(c);
				c.recebimento = ctrlRecebimento.pesquisar(c);
				listaCompra.add(c);
			}
			return listaCompra;
		}
		catch(ClassNotFoundException | SQLException e ){
			throw e;
		}
	}
	
	private Fornecedor getFornecedorDaCompra() {
		Fornecedor forn = actCompra.getListaMateriais().getFirst().getFornecedor();
		return forn;
	}
	
	private boolean validaValorDesconto(double valorEstoque, double valorDesconto) {
		if (valorEstoque >= valorDesconto){
			return true;
		}
		else{
			return false;
		}
	}
	
	private double obterDescontoMinimo (Compra compra) throws ParseException, ClassNotFoundException, SQLException{
		
		double descontoMinimo = 0;
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(actCompra.getData());
		int mes = calendario.get(Calendar.MONTH);
		int ano = calendario.get(Calendar.YEAR);

		SimpleDateFormat sdf =  new SimpleDateFormat ("dd/MM/yyyy");
		Date primeiroDoMes;
		try{
			primeiroDoMes = sdf.parse("01/"+ mes +"/"+ano);
		}
		catch( ParseException e){
			throw e ;
		}
		LinkedList<Compra> lst = new LinkedList<Compra>();
		lst = pesquisar( primeiroDoMes, actCompra.getData());
		if (!lst.isEmpty()){
			Compra auxCompra = null;
			for (Compra c : lst){
				if (auxCompra != null){
					if(c.getDesconto() > auxCompra.getDesconto()){
						auxCompra = c;
					}
				}
				else{
					if (c.getDesconto() > 0){
						auxCompra = c;
					}
				}	 
			}
			descontoMinimo = auxCompra.getDesconto();
		 }
		return descontoMinimo;
	}	
}
package pkgEntity;

import java.util.Date;
import java.util.LinkedList;

public class Venda {
	
	private int id;
	private Date data;
	private double valorVenda;
	private double lucro;
	private LinkedList<Produto> listaProdutos;
	public Cliente cliente;
	public LinkedList<Credito> credito;
	public LinkedList<Entrega> entrega;

	public Venda(){
		super();
	}
	
	public double getLucro() {
		return lucro;
	}

	public void setLucro(double lucro) {
		this.lucro = lucro;
	}

	public LinkedList<Credito> getCredito() {
		return credito;
	}

	public void setCredito(LinkedList<Credito> credito) {
		this.credito = credito;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public LinkedList<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(LinkedList<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LinkedList<Entrega> getEntrega() {
		return entrega;
	}

	public void setEntrega(LinkedList<Entrega> entrega) {
		this.entrega = entrega;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
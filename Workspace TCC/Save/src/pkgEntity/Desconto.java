package pkgEntity;

import java.util.Date;

public class Desconto {
	
	public Fornecedor fornecedor;
	private int id;
	private Date data;
	private double valorDesconto; // Limite superior da faixa de Desconto
	private double desconto; // corresponde a uma fração 0.xx
	
	public Desconto(){
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
}
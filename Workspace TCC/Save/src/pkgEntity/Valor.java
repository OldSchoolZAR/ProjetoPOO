package pkgEntity;

import java.util.Date;

public class Valor {
	
	private int id;
	private Date data;
	private double precoSugerido;
	private int qtdPonto;
	public Produto produto;
	
	public Valor(){
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

	public double getPrecoSugerido() {
		return precoSugerido;
	}
	
	public void setPrecoSugerido(double precoSugerido) {
		this.precoSugerido = precoSugerido;
	}

	public int getQtdPonto() {
		return qtdPonto;
	}
	
	public void setQtdPonto(int qtdPonto) {
		this.qtdPonto = qtdPonto;
	}
	
}
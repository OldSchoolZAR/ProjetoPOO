package pkgEntity;

import java.util.Date;

import pkgEnum.Modalidade;

public abstract class Pagamento {

	protected int id;
	protected Date vencimento;
	protected double valor;
	protected Modalidade modalidade;
	protected boolean foiPago;
	
	public Pagamento(){
		super();
	}

	public Date getVencimento() {
		return vencimento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getModalidade() {
		return modalidade.toString();
	}

	public void setModalidade(String modalidade) {
		this.modalidade = Modalidade.valueOf(modalidade);
	}

	public boolean isFoiPago() {
		return foiPago;
	}

	public void setFoiPago(boolean foiPago) {
		this.foiPago = foiPago;
	}

}
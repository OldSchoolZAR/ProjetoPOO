package pkgEntity;

import java.util.Date;

public abstract class Logistica {

	protected int id;
	protected Date prazoEntrega;
	protected boolean foiEntregue;
	protected Date dataEntrega;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getPrazoEntrega() {
		return prazoEntrega;
	}

	public void setPrazoEntrega(Date prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}

	public boolean isFoiEntregue() {
		return foiEntregue;
	}

	public void setFoiEntregue(boolean foiEntregue) {
		this.foiEntregue = foiEntregue;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	protected String observacao;

	public Logistica(){
		super();
	}

}
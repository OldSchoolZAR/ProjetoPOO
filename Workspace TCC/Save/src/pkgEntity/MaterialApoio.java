package pkgEntity;

public final class MaterialApoio extends Material {
	
	private double preco;
	private int unidade;
	
	public MaterialApoio(){
		super();
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getUnidade() {
		return unidade;
	}

	public void setUnidade(int unidade) {
		this.unidade = unidade;
	}

}
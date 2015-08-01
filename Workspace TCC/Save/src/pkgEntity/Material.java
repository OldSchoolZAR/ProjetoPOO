package pkgEntity;

public abstract class Material {
	
	protected int sku;
	protected int quantidade;
	protected String nome;
	protected boolean brinde;
	protected int quantidadeBrinde;
	protected Fornecedor fornecedor;
	protected Caracteristica caracterisitca;

	public Material(){
		super();
	}
	
	public int getSku() {
		return sku;
	}
	
	public void setSku(int sku) {
		this.sku = sku;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean isBrinde() {
		return brinde;
	}
	
	public void setBrinde(boolean brinde) {
		this.brinde = brinde;
	}
	
	public int getQuantidadeBrinde() {
		return quantidadeBrinde;
	}
	
	public void setQuantidadeBrinde(int quantidadeBrinde) {
		this.quantidadeBrinde = quantidadeBrinde;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public Caracteristica getCaracterisitca() {
		return caracterisitca;
	}
	
	public void setCaracterisitca(Caracteristica caracterisitca) {
		this.caracterisitca = caracterisitca;
	}
	
}
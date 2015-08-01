package pkgEntity;

import pkgEnum.Categorias;

public class Caracteristica {

	private int id;
	private String cor;
	private String volumePeso;
	private String descricao;
	private Categorias categoria;
	private String linha;
	private int sku;
	
	public Caracteristica(){
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getVolumePeso() {
		return volumePeso;
	}

	public void setVolumePeso(String volumePeso) {
		this.volumePeso = volumePeso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCategoria() {
		return categoria.toString();
	}

	public void setCategoria(String categoria) {
		this.categoria = Categorias.valueOf(categoria);
	}

	public String getLinha() {
		return linha;
	}

	public void setLinha(String linha) {
		this.linha = linha;
	}
	
	public int getSku() {
		return sku;
	}

	public void setSku(int sku) {
		this.sku = sku;
	}

}
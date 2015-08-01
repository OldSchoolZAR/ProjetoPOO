package pkgEntity;
import java.util.LinkedList;

import pkgEnum.Fornecedores;

public class Fornecedor {
	
	private int id;
	private Fornecedores nome;
	public LinkedList<Desconto> desconto;
	
	public Fornecedor(){
		super();
	}

	public String getNome() {
		return nome.toString();
	}	public void setNome(String nome) {
		this.nome = Fornecedores.valueOf(nome);
	}	public LinkedList<Desconto> getDesconto() {
		return desconto;
	}
	public void setDesconto(LinkedList<Desconto> desconto) {
		this.desconto = desconto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
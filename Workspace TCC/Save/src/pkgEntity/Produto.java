package pkgEntity;

import java.util.LinkedList;

public class Produto extends Material {

	public LinkedList<Valor> valor;
	
	public Produto(){
		valor = new LinkedList<Valor>();
	}

	public Valor getValor() {
		Valor retValor = valor.get(valor.lastIndexOf(valor));
		return retValor; 
	}
	
	public void setValor(LinkedList<Valor> custo) {
		this.valor = custo;
	}
	
	public LinkedList<Valor> getAllValor(){
		return valor;
	}

}
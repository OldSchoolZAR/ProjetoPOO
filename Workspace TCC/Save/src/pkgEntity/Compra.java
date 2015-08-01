package pkgEntity;
import java.util.Date;
import java.util.LinkedList;

public class Compra {
	
	private int id;
	private Date data;
	private double valorNota;
	private double valorEstoque;
	private double desconto;
	private LinkedList<Material> listaMateriais;
	public LinkedList<Debito> debito;
	public LinkedList<Recebimento> recebimento;
	
	public Compra(){
		id = 0;
		data = null;
		valorNota = 0;
		valorEstoque = 0;
		desconto = 0;
		listaMateriais = new LinkedList<Material>();
		debito = new LinkedList<Debito>();
		recebimento = new LinkedList<Recebimento>();
	}

	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public double getValorNota() {
		return valorNota;
	}
	
	public void setValorNota(double valorNota) {
		this.valorNota = valorNota;
	}
	
	public double getValorEstoque() {
		return valorEstoque;
	}
	
	public void setValorEstoque(double valorEstoque) {
		this.valorEstoque = valorEstoque;
	}
	
	public double getDesconto() {
		return desconto;
	}
	
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	
	public LinkedList<Material> getListaMateriais() {
		return listaMateriais;
	}
	
	public void setListaMateriais(LinkedList<Material> listaMateriais) {
		this.listaMateriais = listaMateriais;
	}
	
	public void setListaMateriais(Material mat){
		this.listaMateriais.add(mat);
	}
	
	public void removeListaMateriais(Material mat){
		this.listaMateriais.remove(mat);
	}
	
	public void setListaBrindes(Material mat){
		this.listaMateriais.add(mat);
	}
	
	public LinkedList<Debito> getDebito() {
		return debito;
	}
	
	public void setDebito(LinkedList<Debito> debito) {
		this.debito = debito;
	}
	
	public LinkedList<Recebimento> getRecebimento() {
		return recebimento;
	}
	
	public void setRecebimento(LinkedList<Recebimento> recebimento) {
		this.recebimento = recebimento;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
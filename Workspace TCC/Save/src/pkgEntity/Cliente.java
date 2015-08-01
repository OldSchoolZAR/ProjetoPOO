package pkgEntity;
import java.util.Date;
import java.util.LinkedList;

public class Cliente
{
	private int	id;
	
	private Date nascimento;
		
	private String telefone;
	
	private String celular;

	private String email;
	
	private String nome;

	public LinkedList<Venda> venda;

	public Cliente(){
		super();
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LinkedList<Venda> getVenda() {
		return venda;
	}

	public void setVenda(LinkedList<Venda> venda) {
		this.venda = venda;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}


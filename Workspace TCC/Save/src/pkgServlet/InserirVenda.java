package pkgServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pkgControles.CtrlProduto;
import pkgControles.CtrlMApoio;
import pkgEntity.Cliente;
import pkgEntity.Credito;
import pkgEntity.Entrega;
import pkgEntity.Material;
import pkgEntity.Venda;

/**
 * Servlet implementation class InserirVenda
 */
@WebServlet("/inserirvenda")
public class InserirVenda extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public void init() throws ServletException{
		CtrlProduto cp = new CtrlProduto();
		CtrlMApoio cm = new CtrlMApoio();
		LinkedList<Material> lst = new LinkedList<Material>();
		try {
			lst.addAll( cp.pesquisarTodos());
			lst.addAll(cm.pesquisarTodos());	
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserirVenda() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Venda venda = viewToVenda(request);
	}


	private Venda viewToVenda(HttpServletRequest request){
		boolean pagamento = false;
		try {
	
		String pago = request.getParameter("rbtnpago");
		if ("Sim".equals(pago)){
			pagamento = true;
		}else if("Não".equals(pago)){
			pagamento = false;
		};
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		Date dataVenda = sdf.parse(request.getParameter("dataVenda"));

		double valorVenda = Double.parseDouble(request.getParameter("valorVenda"));
		String nomeCliente = request.getParameter("nomeCliente");
		String formaPagamento = request.getParameter("sctFPagamento");
		String parcela = request.getParameter("parcelas");

		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Venda venda = new Venda();
		Credito credito = new Credito();
		Entrega entrega = new Entrega();
		Cliente cliente = new Cliente();
		
		/*		venda.setData(data);
		venda.setValorVenda(valorVenda);
		// dependencias
		venda.setCliente(cliente);
		venda.setListaProdutos(listaProdutos);
		venda.setCredito(credito);
		venda.setEntrega(entrega);*/
		
		return venda;
	}
}

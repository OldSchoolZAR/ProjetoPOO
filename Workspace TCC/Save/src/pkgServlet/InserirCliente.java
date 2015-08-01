package pkgServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pkgControles.CtrlCliente;
import pkgEntity.Cliente;

/**
 * Servlet implementation class InserirCliente
 */
@WebServlet("/inserircliente")
public class InserirCliente extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private CtrlCliente ctrlCli;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InserirCliente() {
		super();
		ctrlCli = new CtrlCliente();
		cliente = new Cliente();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Inserir Cliente...");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		cliente.setNome(req.getParameter("txtNome"));
		cliente.setTelefone(req.getParameter("txtFone"));
		cliente.setCelular(req.getParameter("txtCel"));
		cliente.setEmail(req.getParameter("txtEmail"));
		try {
			cliente.setNascimento(sdf.parse(req.getParameter("txtNasc")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(cliente != null){
			boolean ok = false;
			PrintWriter out = res.getWriter();
			try {
				ok = ctrlCli.inserir(cliente);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(ok != false){
				out.println("Registro inserido com sucesso");
			}else{
				out.println("Falha ao inserir registro");
			}
			out.flush();
		}
	}

}
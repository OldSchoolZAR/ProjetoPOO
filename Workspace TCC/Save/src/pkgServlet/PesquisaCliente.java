package pkgServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pkgControles.CtrlCliente;
import pkgEntity.Cliente;

/**
 * Servlet implementation class PesquisaCliente
 */
@WebServlet("/pesqcliente")
public class PesquisaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CtrlCliente ctrlCli;
	private Cliente cli;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PesquisaCliente() {
		super();
		ctrlCli = new CtrlCliente();
		cli = new Cliente();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Pesqusiar Cliente");
		cli.setNome(request.getParameter("txtNome"));
		if(cli != null){
			List<Cliente> clientes = null;
			try {
				clientes = ctrlCli.pesquisar(cli);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			request.setAttribute("CLIENTES", clientes);

			RequestDispatcher rd = request.getRequestDispatcher("./pesqcliente.jsp");
			rd.include( request, response );
		}
		
	}

}
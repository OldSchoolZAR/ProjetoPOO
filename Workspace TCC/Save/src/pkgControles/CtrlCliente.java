package pkgControles;

import java.sql.SQLException;
import java.util.LinkedList;

import pkgDAO.ClienteDAOImpl;
import pkgEntity.Cliente;
import pkgInterface.ClienteDAO;


public class CtrlCliente {
	private ClienteDAO cliDAO;
	
	public CtrlCliente(){
		cliDAO = new ClienteDAOImpl();
	}

	public boolean inserir(Cliente cliente) throws ClassNotFoundException, SQLException {
			if (validaCriterio(cliente)){
				return cliDAO.inserir(cliente);
			}
			else{
				return false;
			}
	}
	public boolean atualizar(Cliente cliente) throws ClassNotFoundException, SQLException {
		if (validaCriterio(cliente)){
			return cliDAO.atualizar(cliente);
		} 
		else{
			return false;
		}
	}
	public LinkedList<Cliente> pesquisar(Cliente cliente) throws ClassNotFoundException, SQLException {
		LinkedList<Cliente> lstCliente = null;
		if(validaCriterio(cliente)){
        lstCliente = cliDAO.pesquisar(cliente);
    }
    return lstCliente;
}
	public LinkedList<Cliente> pesquisaPorNome(Cliente cliente) throws ClassNotFoundException, SQLException{
		
		LinkedList<Cliente> lstCliente = null;
		if(validaCriterio(cliente)){
				lstCliente = cliDAO.pesquisar(cliente);
		}
		return lstCliente;
	}
	public LinkedList<Cliente> pesquisaPorData(Cliente cliente) throws ClassNotFoundException{
		LinkedList<Cliente> lstCliente = null;
		if(validaCriterio(cliente)){
			try {
				lstCliente =  cliDAO.pesquisar(cliente);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lstCliente;
	}
	public LinkedList<Cliente> pesquisarTodos() throws ClassNotFoundException, SQLException{
		LinkedList<Cliente> lstCliente = null;
				lstCliente =  cliDAO.pesquisarTodos();
		return lstCliente;
	}

	private boolean validaCriterio(Cliente cli){
		if(cli == null){
			return false;
		}
		if("".equals(cli.getNome())){
			return false;
		}
		if("".equals(cli.getCelular()) && "".equals(cli.getTelefone())){
			return false;
		}
		return true;
	}
}
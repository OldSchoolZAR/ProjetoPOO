package pkgDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import pkgEntity.Venda;
import pkgInterface.ClienteDAO;
import pkgInterface.CreditoDAO;
import pkgInterface.EntregaDAO;
import pkgInterface.VendaDAO;
import pkgUtil.CriaConexao;

public class VendaDAOImpl implements VendaDAO {

	public VendaDAOImpl() {
		super();
	}
	
	private ItemVendaDAOImpl ivDAO;
	private CreditoDAO creDAO;
	private EntregaDAO entDAO;
	private ClienteDAO cliDAO;

	@Override
	public boolean inserir(Venda venda) throws ClassNotFoundException, SQLException {
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "INSERT INTO VENDA (DATA, VALOR_VENDA, CLIENTE_ID_CLIENTE, LUCRO) " +
					 "VALUES (?, ? , ?, ?);";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!venda.equals(null)){
			ps.setDate(1, new java.sql.Date(venda.getData().getTime()));
			ps.setDouble(2, venda.getValorVenda());
			ps.setInt(3, venda.getCliente().getId());
			ps.setDouble(4, venda.getLucro());
			if(ps.executeUpdate() > 0){
				retorno = true;
			}
		}
		criaCon.fecharConexao();
		ivDAO.inserir(venda);
		creDAO.inserir(venda);
		entDAO.inserir(venda);
		return retorno;
	}

	@Override
	public boolean atualizar(Venda venda) throws ClassNotFoundException, SQLException {
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "UPDATE VENDA SET (DATA, VALOR_VENDA, CLIENTE_ID_CLIENTE, LUCRO) " +
					 "VALUES (?, ? , ?, ?)" +
					 "WHERE ID_VENDA = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!venda.equals(null)){
			ps.setDate(1, new java.sql.Date(venda.getData().getTime()));
			ps.setDouble(2, venda.getValorVenda());
			ps.setInt(3, venda.getCliente().getId());
			ps.setDouble(4, venda.getLucro());
			ps.setInt(5, venda.getId());
			if(ps.executeUpdate() > 0){
				retorno = true;
			}
		}
		criaCon.fecharConexao();
		ivDAO.atualizar(venda);
		creDAO.atualizar(venda);
		entDAO.atualizar(venda);
		return retorno;
	}
	
	@Override
	public LinkedList<Venda> pesquisar(Venda venda) throws ClassNotFoundException, SQLException {
		LinkedList<Venda> listaVenda = new LinkedList<Venda>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_VENDA, DATA, VALOR_VENDA, CLIENTE_ID_CLIENTE, LUCRO) " +
					 "FROM VENDA " +
					 "WHERE DATA = ? " +
					 "ORDER BY DATA DESC;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!venda.equals(null)){
			ps.setDate(1, new java.sql.Date(venda.getData().getTime()));
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Venda vnd = new Venda();
				vnd.setId(rs.getInt("ID_VENDA"));
				vnd.setData(new java.util.Date(rs.getDate("DATA").getTime()));
				vnd.setValorVenda(rs.getDouble("VALOR_VENDA"));
				vnd.cliente.setId(rs.getInt("CLIENTE_ID_CLIENTE"));
				vnd.setLucro(rs.getDouble("LUCRO"));
				listaVenda.add(vnd);
			}
		}
		criaCon.fecharConexao();
		for(Venda v: listaVenda){
			v.setListaProdutos(ivDAO.pesquisar(v));
			v.setCredito(creDAO.pesquisar(v));
			v.setEntrega(entDAO.pesquisar(v));
			v.setCliente(cliDAO.pesquisar(v.cliente.getId()));
		}
		return listaVenda;
	}
	
	@Override
	public LinkedList<Venda> pesquisar(Date dataInicio, Date dataFim)
			throws ClassNotFoundException, SQLException {
		LinkedList<Venda> listaVenda = new LinkedList<Venda>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_VENDA, DATA, VALOR_VENDA, CLIENTE_ID_CLIENTE, LUCRO) " +
					 "FROM VENDA " +
					 "WHERE DATA BETWEEN ? AND ? " +
					 "ORDER BY DATA DESC;";
		PreparedStatement ps = con.prepareStatement(sql);
		if((!dataInicio.equals(null)) && (!dataFim.equals(null))){
			if(dataInicio.before(dataFim)){
				ps.setDate(1, new java.sql.Date(dataInicio.getTime()));
				ps.setDate(2, new java.sql.Date(dataFim.getTime()));
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Venda vnd = new Venda();
					vnd.setId(rs.getInt("ID_VENDA"));
					vnd.setData(new java.util.Date(rs.getDate("DATA").getTime()));
					vnd.setValorVenda(rs.getDouble("VALOR_VENDA"));
					vnd.cliente.setId(rs.getInt("CLIENTE_ID_CLIENTE"));
					vnd.setLucro(rs.getDouble("LUCRO"));
					listaVenda.add(vnd);
				}
			}
		}
		criaCon.fecharConexao();
		for(Venda v: listaVenda){
			v.setListaProdutos(ivDAO.pesquisar(v));
			v.setCredito(creDAO.pesquisar(v));
			v.setEntrega(entDAO.pesquisar(v));
			v.setCliente(cliDAO.pesquisar(v.cliente.getId()));
		}
		return listaVenda;
	}

	@Override
	public LinkedList<Venda> pesquisarTodos() throws ClassNotFoundException, SQLException {
		LinkedList<Venda> listaVenda = new LinkedList<Venda>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_VENDA, DATA, VALOR_VENDA, CLIENTE_ID_CLIENTE, LUCRO) " +
					 "FROM VENDA " +
					 "ORDER BY DATA DESC;";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Venda vnd = new Venda();
			vnd.setId(rs.getInt("ID_VENDA"));
			vnd.setData(new java.util.Date(rs.getDate("DATA").getTime()));
			vnd.setValorVenda(rs.getDouble("VALOR_VENDA"));
			vnd.cliente.setId(rs.getInt("CLIENTE_ID_CLIENTE"));
			vnd.setLucro(rs.getDouble("LUCRO"));
			listaVenda.add(vnd);
		}
		criaCon.fecharConexao();
		for(Venda v: listaVenda){
			v.setListaProdutos(ivDAO.pesquisar(v));
			v.setCredito(creDAO.pesquisar(v));
			v.setEntrega(entDAO.pesquisar(v));
			v.setCliente(cliDAO.pesquisar(v.cliente.getId()));
		}
		return listaVenda;
	}

}
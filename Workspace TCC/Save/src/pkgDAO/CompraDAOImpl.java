package pkgDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import pkgEntity.Compra;
import pkgInterface.CompraDAO;
import pkgInterface.DebitoDAO;
import pkgInterface.RecebimentoDAO;
import pkgUtil.CriaConexao;

public class CompraDAOImpl implements CompraDAO {
	
	private ItemCompraDAOImpl icDAO;
	private DebitoDAO debitoDAO;
	private RecebimentoDAO recebDAO;

	public CompraDAOImpl() {
		super();
	}

	@Override
	public boolean inserir(Compra compra) throws ClassNotFoundException, SQLException {
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "INSERT INTO COMPRA (DATA, VALOR_NOTA, VALOR_ESTOQUE, DESCONTO) " +
					 "VALUES (?, ? , ?, ?);";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!compra.equals(null)){
			ps.setDate(1, new java.sql.Date(compra.getData().getTime()));
			ps.setDouble(2, compra.getValorNota());
			ps.setDouble(3, compra.getValorEstoque());
			ps.setDouble(4, compra.getDesconto());
			if(ps.executeUpdate() > 0){
				retorno = true;
			}
		}
		criaCon.fecharConexao();
		icDAO.inserir(compra);
		debitoDAO.inserir(compra);
		recebDAO.inserir(compra);
		return retorno;
	}
	
	@Override
	public boolean atualizar(Compra compra) throws ClassNotFoundException, SQLException {
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "UPDATE COMPRA SET (DATA, VALOR_NOTA, VALOR_ESTOQUE, DESCONTO) " +
					 "VALUES (?, ? , ?, ?) " +
					 "WHERE ID_COMPRA = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!compra.equals(null)){
			ps.setDate(1, new java.sql.Date(compra.getData().getTime()));
			ps.setDouble(2, compra.getValorNota());
			ps.setDouble(3, compra.getValorEstoque());
			ps.setDouble(4, compra.getDesconto());
			ps.setInt(5, compra.getId());
			if(ps.executeUpdate() > 0){
				retorno = true;
			}
		}
		criaCon.fecharConexao();
		icDAO.atualizar(compra);
		debitoDAO.atualizar(compra);
		recebDAO.atualizar(compra);
		return retorno;
	}

	@Override
	public LinkedList<Compra> pesquisar(Compra compra) throws ClassNotFoundException, SQLException {
		LinkedList<Compra> listaCompra = new LinkedList<Compra>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_COMPRA, DATA, VALOR_NOTA, VALOR_ESTOQUE, DESCONTO) " +
					 "FROM COMPRA " +
					 "WHERE DATA = ? " +
					 "ORDER BY DATA DESC;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!compra.equals(null)){
			ps.setDate(1, new java.sql.Date(compra.getData().getTime()));
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Compra cmp = new Compra();
				cmp.setId(rs.getInt("ID_COMPRA"));
				cmp.setData(new java.util.Date(rs.getDate("DATA").getTime()));
				cmp.setValorNota(rs.getDouble("VALOR_NOTA"));
				cmp.setValorEstoque(rs.getDouble("VALOR_ESTOQUE"));
				cmp.setDesconto(rs.getDouble("DESCONTO"));
				listaCompra.add(cmp);
			}
		}
		criaCon.fecharConexao();
		for(Compra c: listaCompra){
			c.setListaMateriais(icDAO.pesquisar(c));
			c.setDebito(debitoDAO.pesquisar(c));
			c.setRecebimento(recebDAO.pesquisar(c));
		}
		return listaCompra;
	}
	
	@Override
	public LinkedList<Compra> pesquisar(Date dataInicio, Date dataFim) 
			throws ClassNotFoundException, SQLException {
		LinkedList<Compra> listaCompra = new LinkedList<Compra>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_COMPRA, DATA, VALOR_NOTA, VALOR_ESTOQUE, DESCONTO) " +
					 "FROM COMPRA " +
					 "WHERE DATA BETWEEN ? AND ? " +
					 "ORDER BY DATA DESC;";
		PreparedStatement ps = con.prepareStatement(sql);
		if((!dataInicio.equals(null)) && (!dataFim.equals(null))){
			if(dataInicio.before(dataFim)){
				ps.setDate(1, new java.sql.Date(dataInicio.getTime()));
				ps.setDate(2, new java.sql.Date(dataFim.getTime()));
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Compra cmp = new Compra();
					cmp.setId(rs.getInt("ID_COMPRA"));
					cmp.setData(new java.util.Date(rs.getDate("DATA").getTime()));
					cmp.setValorNota(rs.getDouble("VALOR_NOTA"));
					cmp.setValorEstoque(rs.getDouble("VALOR_ESTOQUE"));
					cmp.setDesconto(rs.getDouble("DESCONTO"));
					listaCompra.add(cmp);
				}
			}
		}
		criaCon.fecharConexao();
		for(Compra c: listaCompra){
			c.setListaMateriais(icDAO.pesquisar(c));
			c.setDebito(debitoDAO.pesquisar(c));
			c.setRecebimento(recebDAO.pesquisar(c));
		}
		return listaCompra;
	}
	
	@Override
	public LinkedList<Compra> pesquisarTodos() throws ClassNotFoundException, SQLException {
		LinkedList<Compra> listaCompra = new LinkedList<Compra>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_COMPRA, DATA, VALOR_NOTA, VALOR_ESTOQUE, DESCONTO) " +
					 "FROM COMPRA " +
					 "ORDER BY DATA DESC;";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Compra cmp = new Compra();
			cmp.setId(rs.getInt("ID_COMPRA"));
			cmp.setData(new java.util.Date(rs.getDate("DATA").getTime()));
			cmp.setValorNota(rs.getDouble("VALOR_NOTA"));
			cmp.setValorEstoque(rs.getDouble("VALOR_ESTOQUE"));
			cmp.setDesconto(rs.getDouble("DESCONTO"));
			listaCompra.add(cmp);
		}
		criaCon.fecharConexao();
		for(Compra c: listaCompra){
			c.setListaMateriais(icDAO.pesquisar(c));
			c.setDebito(debitoDAO.pesquisar(c));
			c.setRecebimento(recebDAO.pesquisar(c));
		}
		return listaCompra;
	}

}
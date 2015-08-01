package pkgDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import pkgEntity.Credito;
import pkgEntity.Venda;
import pkgInterface.CreditoDAO;
import pkgUtil.CriaConexao;

public class CreditoDAOImpl implements CreditoDAO {

	public CreditoDAOImpl() {
		super();
	}

	@Override
	public boolean inserir(Venda venda) throws SQLException, ClassNotFoundException {
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "INSERT INTO CREDITO (VENCIMENTO, VALOR, MODALIDADE, FOI_PAGO, VENDA_ID_VENDA) " +
					 "VALUES (?, ? , ?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!venda.getCredito().isEmpty()){
			for(Credito c: venda.getCredito()){
				ps.setDate(1, new java.sql.Date(c.getVencimento().getTime()));
				ps.setDouble(2, c.getValor());
				ps.setString(3, c.getModalidade());
				ps.setBoolean(4, c.isFoiPago());
				ps.setInt(5, venda.getId());
				if(ps.executeUpdate() > 0){
					retorno = true;
				}
			}
		}
		criaCon.fecharConexao();
		return retorno;
	}

	@Override
	public boolean atualizar(Venda venda) throws ClassNotFoundException, SQLException {
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "UPDATE CREDITO SET (VENCIMENTO, VALOR, MODALIDADE, FOI_PAGO, VENDA_ID_VENDA) " +
					 "VALUES (?, ? , ?, ?, ?) " +
					 "WHERE ID_CREDITO = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!venda.getCredito().isEmpty()){
			for(Credito c: venda.getCredito()){
				ps.setDate(1, new java.sql.Date(c.getVencimento().getTime()));
				ps.setDouble(2, c.getValor());
				ps.setString(3, c.getModalidade());
				ps.setBoolean(4, c.isFoiPago());
				ps.setInt(5, venda.getId());
				ps.setInt(6, c.getId());
				if(ps.executeUpdate() > 0){
					retorno = true;
				}
			}
		}
		criaCon.fecharConexao();
		return retorno;
	}

	@Override
	public LinkedList<Credito> pesquisar(Venda venda) throws ClassNotFoundException, SQLException {
		LinkedList<Credito> listaCredito = new LinkedList<Credito>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_CREDITO, VENCIMENTO, VALOR, MODALIDADE, FOI_PAGO, VENDA_ID_VENDA) " +
					 "FROM CREDITO " +
					 "WHERE VENDA_ID_VENDA = ? " +
					 "ORDER BY VENCIMENTO DESC;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!venda.getCredito().isEmpty()){
			ps.setInt(1, venda.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Credito cdt = new Credito();
				cdt.setId(rs.getInt("ID_CREDITO"));
				cdt.setVencimento(new java.util.Date(rs.getDate("VENCIMENTO").getTime()));
				cdt.setValor(rs.getDouble("VALOR"));
				cdt.setModalidade(rs.getString("MODALIDADE"));
				cdt.setFoiPago(rs.getBoolean("FOI_PAGO"));
				cdt.venda.setId(rs.getInt("VENDA_ID_VENDA"));
				listaCredito.add(cdt);
			}
		}
		criaCon.fecharConexao();
		return listaCredito;
	}

	@Override
	public LinkedList<Credito> pesquisar(Date dataInicio, Date dataFim)
			throws SQLException, ClassNotFoundException {
		LinkedList<Credito> listaCredito = new LinkedList<Credito>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_CREDITO, VENCIMENTO, VALOR, MODALIDADE, FOI_PAGO, VENDA_ID_VENDA) " +
					 "FROM CREDITO " +
					 "WHERE VENCIMENTO BETWEEN ? AND ? " +
					 "ORDER BY VENCIMENTO DESC;";
		PreparedStatement ps = con.prepareStatement(sql);
		if((!dataInicio.equals(null)) && (!dataFim.equals(null))){
			if(dataInicio.before(dataFim)){
				ps.setDate(1, new java.sql.Date(dataInicio.getTime()));
				ps.setDate(2, new java.sql.Date(dataFim.getTime()));
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Credito cdt = new Credito();
					cdt.setId(rs.getInt("ID_CREDITO"));
					cdt.setVencimento(new java.util.Date(rs.getDate("VENCIMENTO").getTime()));
					cdt.setValor(rs.getDouble("VALOR"));
					cdt.setModalidade(rs.getString("MODALIDADE"));
					cdt.setFoiPago(rs.getBoolean("FOI_PAGO"));
					cdt.venda.setId(rs.getInt("VENDA_ID_VENDA"));
					listaCredito.add(cdt);
				}
			}
		}
		criaCon.fecharConexao();
		return listaCredito;
	}

	@Override
	public LinkedList<Credito> pesquisarTodos() throws ClassNotFoundException,
			SQLException {
		LinkedList<Credito> listaCredito = new LinkedList<Credito>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_CREDITO, VENCIMENTO, VALOR, MODALIDADE, FOI_PAGO, VENDA_ID_VENDA) " +
					 "FROM CREDITO " +
					 "ORDER BY VENCIMENTO DESC;";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Credito cdt = new Credito();
			cdt.setId(rs.getInt("ID_CREDITO"));
			cdt.setVencimento(new java.util.Date(rs.getDate("VENCIMENTO").getTime()));
			cdt.setValor(rs.getDouble("VALOR"));
			cdt.setModalidade(rs.getString("MODALIDADE"));
			cdt.setFoiPago(rs.getBoolean("FOI_PAGO"));
			cdt.venda.setId(rs.getInt("VENDA_ID_VENDA"));
			listaCredito.add(cdt);
		}
		criaCon.fecharConexao();
		return listaCredito;
	}

}
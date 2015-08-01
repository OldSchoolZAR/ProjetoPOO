package pkgDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import pkgEntity.Compra;
import pkgEntity.Debito;
import pkgInterface.DebitoDAO;
import pkgUtil.CriaConexao;

public class DebitoDAOImpl implements DebitoDAO {

	public DebitoDAOImpl() {
		super();
	}

	@Override
	public boolean inserir(Compra compra) throws ClassNotFoundException, SQLException {
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "INSERT INTO DEBITO (VENCIMENTO, VALOR, MODALIDADE, FOI_PAGO, COMPRA_ID_COMPRA) " +
					 "VALUES (?, ? , ?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(sql);
		if (!compra.getDebito().isEmpty()){
			for(Debito d: compra.getDebito()){
				ps.setDate(1, new java.sql.Date(d.getVencimento().getTime() ));
				ps.setDouble(2, d.getValor());
				ps.setString(3, d.getModalidade());
				ps.setBoolean(4, d.isFoiPago());
				ps.setInt(5, compra.getId());
				if(ps.executeUpdate() > 0){
					retorno = true;
				}
			}
			criaCon.fecharConexao();
		}
		return retorno;
	}

	@Override
	public boolean atualizar(Compra compra) throws ClassNotFoundException, SQLException {
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "UPDATE DEBITO SET (VENCIMENTO, VALOR, MODALIDADE, FOI_PAGO, COMPRA_ID_COMPRA) " +
					 "VALUES (?, ? , ?, ?, ?) " +
					 "WHERE ID_DEBITO = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if (!compra.getDebito().isEmpty()){
			for(Debito d: compra.getDebito()){
				ps.setDate(1, new java.sql.Date(d.getVencimento().getTime() ));
				ps.setDouble(2, d.getValor());
				ps.setString(3, d.getModalidade());
				ps.setBoolean(4, d.isFoiPago());
				ps.setInt(5, compra.getId());
				ps.setInt(6, d.getId());
				if(ps.executeUpdate() > 0){
					retorno = true;
				}
			}
			criaCon.fecharConexao();
		}
		return retorno;
	}

	@Override
	public LinkedList<Debito> pesquisar(Compra compra) throws ClassNotFoundException, SQLException {
		LinkedList<Debito> listaDebito = new LinkedList<Debito>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_DEBITO, VENCIMENTO, VALOR, MODALIDADE, FOI_PAGO, COMPRA_ID_COMPRA) " +
					 "FROM DEBITO " +
					 "WHERE COMPRA_ID_COMPRA = ? " +
					 "ORDER BY VENCIMENTO DESC;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!compra.getDebito().isEmpty()){
			ps.setInt(1, compra.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Debito dbt = new Debito();
				dbt.setId(rs.getInt("ID_DEBITO"));
				dbt.setVencimento(new java.util.Date(rs.getDate("VENCIMENTO").getTime()));
				dbt.setValor(rs.getDouble("VALOR"));
				dbt.setModalidade(rs.getString("MODALIDADE"));
				dbt.setFoiPago(rs.getBoolean("FOI_PAGO"));
				dbt.compra.setId(rs.getInt("COMPRA_ID_COMPRA"));
				listaDebito.add(dbt);
			}
		}
		criaCon.fecharConexao();
		return listaDebito;
	}

	@Override
	public LinkedList<Debito> pesquisar(Date dataInicio, Date dataFim) throws ClassNotFoundException, SQLException {
		LinkedList<Debito> listaDebito = new LinkedList<Debito>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_DEBITO, VENCIMENTO, VALOR, MODALIDADE, FOI_PAGO, COMPRA_ID_COMPRA) " +
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
					Debito dbt = new Debito();
					dbt.setId(rs.getInt("ID_DEBITO"));
					dbt.setVencimento(new java.util.Date(rs.getDate("VENCIMENTO").getTime()));
					dbt.setValor(rs.getDouble("VALOR"));
					dbt.setModalidade(rs.getString("MODALIDADE"));
					dbt.setFoiPago(rs.getBoolean("FOI_PAGO"));
					dbt.compra.setId(rs.getInt("COMPRA_ID_COMPRA"));
					listaDebito.add(dbt);
				}
			}
		}
		criaCon.fecharConexao();
		return listaDebito;
	}

	@Override
	public LinkedList<Debito> pesquisarTodos() throws ClassNotFoundException,
			SQLException {
		LinkedList<Debito> listaDebito = new LinkedList<Debito>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_DEBITO, VENCIMENTO, VALOR, MODALIDADE, FOI_PAGO, COMPRA_ID_COMPRA) " +
					 "FROM DEBITO " +
					 "ORDER BY VENCIMENTO DESC;";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Debito dbt = new Debito();
			dbt.setId(rs.getInt("ID_DEBITO"));
			dbt.setVencimento(new java.util.Date(rs.getDate("VENCIMENTO").getTime()));
			dbt.setValor(rs.getDouble("VALOR"));
			dbt.setModalidade(rs.getString("MODALIDADE"));
			dbt.setFoiPago(rs.getBoolean("FOI_PAGO"));
			dbt.compra.setId(rs.getInt("COMPRA_ID_COMPRA"));
			listaDebito.add(dbt);
		}
		criaCon.fecharConexao();
		return listaDebito;
	}

}
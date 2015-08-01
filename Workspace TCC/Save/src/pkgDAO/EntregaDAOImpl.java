package pkgDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import pkgEntity.Entrega;
import pkgEntity.Venda;
import pkgInterface.EntregaDAO;
import pkgUtil.CriaConexao;

public class EntregaDAOImpl implements EntregaDAO {

	public EntregaDAOImpl() {
		super();
	}

	@Override
	public boolean inserir(Venda venda) throws ClassNotFoundException, SQLException {
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "INSERT INTO ENTREGA (PRAZO, DATA, FOI_ENTREGUE, OBS, VENDA_ID_VENDA) " +
					 "VALUES (?, ? , ?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(sql);
		if (!venda.getEntrega().isEmpty()){
			for(Entrega e: venda.getEntrega()){
				ps.setDate(1, new java.sql.Date(e.getPrazoEntrega().getTime()));
				ps.setDate(2, new java.sql.Date(e.getDataEntrega().getTime()));
				ps.setBoolean(3, e.isFoiEntregue());
				ps.setString(4, e.getObservacao());
				ps.setInt(5, venda.getId());
				if(ps.executeUpdate() > 0){
					retorno = true;
				}
			}
			criaCon.fecharConexao();
		}
		return retorno;
	}

	@Override
	public boolean atualizar(Venda venda) throws ClassNotFoundException, SQLException {
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "UPDATE ENTREGA SET (PRAZO, DATA, FOI_ENTREGUE, OBS, VENDA_ID_VENDA) " +
					 "VALUES (?, ? , ?, ?, ?) " +
					 "WHERE ID_ENTREGA = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if (!venda.getEntrega().isEmpty()){
			for(Entrega e: venda.getEntrega()){
				ps.setDate(1, new java.sql.Date(e.getPrazoEntrega().getTime()));
				ps.setDate(2, new java.sql.Date(e.getDataEntrega().getTime()));
				ps.setBoolean(3, e.isFoiEntregue());
				ps.setString(4, e.getObservacao());
				ps.setInt(5, venda.getId());
				ps.setInt(6, e.getId());
				if(ps.executeUpdate() > 0){
					retorno = true;
				}
			}
			criaCon.fecharConexao();
		}
		return retorno;
	}

	@Override
	public LinkedList<Entrega> pesquisar(Venda venda) throws ClassNotFoundException, SQLException {
		LinkedList<Entrega> listaEntrega = new LinkedList<Entrega>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_ENTREGA, PRAZO, DATA, FOI_ENTREGUE, OBS, VENDA_ID_VENDA) " +
					 "FROM ENTREGA " +
					 "WHERE VENDA_ID_VENDA = ? " +
					 "ORDER BY PRAZO DESC;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!venda.equals(null)){
			ps.setInt(1, venda.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Entrega etg = new Entrega();
				etg.setId(rs.getInt("ID_ENTREGA"));
				etg.setPrazoEntrega(new java.util.Date(rs.getDate("PRAZO").getTime()));
				etg.setDataEntrega(new java.util.Date(rs.getDate("DATA").getTime()));
				etg.setFoiEntregue(rs.getBoolean("FOI_ENTREGUE"));
				etg.setObservacao(rs.getString("OBS"));
				etg.venda.setId(rs.getInt("VENDA_ID_VENDA"));
				listaEntrega.add(etg);
			}
		}
		criaCon.fecharConexao();
		return listaEntrega;
	}

	@Override
	public LinkedList<Entrega> pesquisar(Date dataInicio, Date dataFim) throws ClassNotFoundException, SQLException {
		LinkedList<Entrega> listaEntrega = new LinkedList<Entrega>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_ENTREGA, PRAZO, DATA, FOI_ENTREGUE, OBS, VENDA_ID_VENDA) " +
					 "FROM ENTREGA " +
					 "WHERE PRAZO BETWEEN ? AND ? " +
					 "ORDER BY PRAZO DESC;";
		PreparedStatement ps = con.prepareStatement(sql);
		if((!dataInicio.equals(null)) && (!dataFim.equals(null))){
			if(dataInicio.before(dataFim)){
				ps.setDate(1, new java.sql.Date(dataInicio.getTime()));
				ps.setDate(2, new java.sql.Date(dataFim.getTime()));
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Entrega etg = new Entrega();
					etg.setId(rs.getInt("ID_ENTREGA"));
					etg.setPrazoEntrega(new java.util.Date(rs.getDate("PRAZO").getTime()));
					etg.setDataEntrega(new java.util.Date(rs.getDate("DATA").getTime()));
					etg.setFoiEntregue(rs.getBoolean("FOI_ENTREGUE"));
					etg.setObservacao(rs.getString("OBS"));
					etg.venda.setId(rs.getInt("VENDA_ID_VENDA"));
					listaEntrega.add(etg);
				}
			}
		}
		criaCon.fecharConexao();
		return listaEntrega;
	}

	@Override
	public LinkedList<Entrega> pesquisarTodos() throws ClassNotFoundException, SQLException {
		LinkedList<Entrega> listaEntrega = new LinkedList<Entrega>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_ENTREGA, PRAZO, DATA, FOI_ENTREGUE, OBS, VENDA_ID_VENDA) " +
					 "FROM ENTREGA " +
					 "ORDER BY PRAZO DESC;";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Entrega etg = new Entrega();
			etg.setId(rs.getInt("ID_ENTREGA"));
			etg.setPrazoEntrega(new java.util.Date(rs.getDate("PRAZO").getTime()));
			etg.setDataEntrega(new java.util.Date(rs.getDate("DATA").getTime()));
			etg.setFoiEntregue(rs.getBoolean("FOI_ENTREGUE"));
			etg.setObservacao(rs.getString("OBS"));
			etg.venda.setId(rs.getInt("VENDA_ID_VENDA"));
			listaEntrega.add(etg);
		}
		criaCon.fecharConexao();
		return listaEntrega;
	}

}
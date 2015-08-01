package pkgDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import pkgEntity.Compra;
import pkgEntity.Recebimento;
import pkgInterface.RecebimentoDAO;
import pkgUtil.CriaConexao;

public class RecebimentoDAOImpl implements RecebimentoDAO {

	public RecebimentoDAOImpl() {
		super();
	}

	@Override
	public boolean inserir(Compra compra) throws ClassNotFoundException, SQLException {
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "INSERT INTO RECEBIMENTO (PRAZO, DATA, FOI_ENTREGUE, OBS, COMPRA_ID_COMPRA) " +
					 "VALUES (?, ? , ?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(sql);
		if (!compra.getRecebimento().isEmpty()){
			for(Recebimento r: compra.getRecebimento()){
				ps.setDate(1, new java.sql.Date(r.getPrazoEntrega().getTime()));
				ps.setDate(2, new java.sql.Date(r.getDataEntrega().getTime()));
				ps.setBoolean(3, r.isFoiEntregue());
				ps.setString(4, r.getObservacao());
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
		String sql = "UPDATE RECEBIMENTO SET (PRAZO, DATA, FOI_ENTREGUE, OBS, VENDA_ID_VENDA) " +
					 "VALUES (?, ? , ?, ?, ?) " +
					 "WHERE ID_RECEBIMENTO = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if (!compra.getRecebimento().isEmpty()){
			for(Recebimento r: compra.getRecebimento()){
				ps.setDate(1, new java.sql.Date(r.getPrazoEntrega().getTime()));
				ps.setDate(2, new java.sql.Date(r.getDataEntrega().getTime()));
				ps.setBoolean(3, r.isFoiEntregue());
				ps.setString(4, r.getObservacao());
				ps.setInt(5, compra.getId());
				ps.setInt(6, r.getId());
				if(ps.executeUpdate() > 0){
					retorno = true;
				}
			}
			criaCon.fecharConexao();
		}
		return retorno;
	}
	@Override
	public LinkedList<Recebimento> pesquisar(Compra compra) throws ClassNotFoundException, SQLException {
		LinkedList<Recebimento> listaRecebimento = new LinkedList<Recebimento>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_RECEBIMENTO, PRAZO, DATA, FOI_ENTREGUE, OBS, COMPRA_ID_COMPRA) " +
					 "FROM RECEBIMENTO " +
					 "WHERE COMPRA_ID_COMPRA = ? " +
					 "ORDER BY PRAZO DESC;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(compra.equals(null)){
			ps.setInt(1, compra.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Recebimento rcb = new Recebimento();
				rcb.setId(rs.getInt("ID_RECEBIMENTO"));
				rcb.setPrazoEntrega(new java.util.Date(rs.getDate("PRAZO").getTime()));
				rcb.setDataEntrega(new java.util.Date(rs.getDate("DATA").getTime()));
				rcb.setFoiEntregue(rs.getBoolean("FOI_ENTREGUE"));
				rcb.setObservacao(rs.getString("OBS"));
				rcb.compra.setId(rs.getInt("VENDA_ID_VENDA"));
				listaRecebimento.add(rcb);
			}
		}
		criaCon.fecharConexao();
		return listaRecebimento;
	}

}
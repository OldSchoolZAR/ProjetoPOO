package pkgDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import pkgEntity.Desconto;
import pkgEntity.Fornecedor;
import pkgUtil.CriaConexao;

public class DescontoDAOImpl {

	public boolean inserir(Fornecedor fornecedor) throws ClassNotFoundException, SQLException{
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "INSERT INTO DESCONTO (DATA, VALOR_DESCONTO, DESCONTO, FORNECEDOR_ID_FORNECEDOR) " +
					 "VALUES (?, ?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(sql);
		if (!fornecedor.getDesconto().isEmpty()){
			for(Desconto d: fornecedor.getDesconto()){
				ps.setDate(1, new java.sql.Date(d.getData().getTime()));
				ps.setDouble(2, d.getValorDesconto());
				ps.setDouble(3, d.getDesconto());
				ps.setInt(4, fornecedor.getId());
				if(ps.executeUpdate() > 0){
					retorno = true;
				}
			}
			criaCon.fecharConexao();
		}
		return retorno;
	}
	
	public boolean atualizar(Fornecedor fornecedor) throws ClassNotFoundException, SQLException{
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "UPDATE DESCONTO SET (DATA, VALOR_DESCONTO, DESCONTO, FORNECEDOR_ID_FORNECEDOR) " +
					 "VALUES (?, ?, ?, ?)" +
					 "WHERE ID_DESCONTO = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if (!fornecedor.getDesconto().isEmpty()){
			for(Desconto d: fornecedor.getDesconto()){
				ps.setDate(1, new java.sql.Date(d.getData().getTime()));
				ps.setDouble(2, d.getValorDesconto());
				ps.setDouble(3, d.getDesconto());
				ps.setInt(4, fornecedor.getId());
				ps.setInt(5, d.getId());
				if(ps.executeUpdate() > 0){
					retorno = true;
				}
			}
			criaCon.fecharConexao();
		}
		return retorno;
	}
	
	public LinkedList<Desconto> pesquisar(Fornecedor fornecedor) throws ClassNotFoundException, SQLException{
		LinkedList<Desconto> listaDesconto = new LinkedList<Desconto>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_DESCONTO, DATA, VALOR_DESCONTO, DESCONTO, FORNECEDOR_ID_FORNECEDOR) " +
					 "FROM DESCONTO " +
					 "WHERE ID_FORNECEDOR = ? " +
					 "ORDER BY DATA DESC;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!fornecedor.equals(null)){
			ps.setInt(1, fornecedor.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Desconto d = new Desconto();
				d.setId(rs.getInt("ID_DESCONTO"));
				d.setData(new java.util.Date(rs.getDate("DATA").getTime()));
				d.setValorDesconto(rs.getDouble("VALOR_DESCONTO"));
				d.setDesconto(rs.getDouble("DESCONTO"));
				d.fornecedor.setId(rs.getInt("FORNECEDOR_ID_FORNECEDOR"));
				listaDesconto.add(d);
			}
		}
		criaCon.fecharConexao();
		return listaDesconto;
	}
	
}
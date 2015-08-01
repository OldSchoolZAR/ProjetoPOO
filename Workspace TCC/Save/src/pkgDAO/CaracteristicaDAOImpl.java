package pkgDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pkgEntity.Caracteristica;
import pkgEntity.Material;
import pkgUtil.CriaConexao;

public class CaracteristicaDAOImpl {
	
	public boolean inserir(Material material) throws ClassNotFoundException, SQLException{
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "INSERT INTO CARACTERISTICA (COR, VOLUME_PESO, DESCRICAO, CATEGORIA, LINHA, SKU) " +
					 "VALUES (?, ?, ?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!material.getCaracterisitca().equals(null)){
			ps.setString(1, material.getCaracterisitca().getCor());
			ps.setString(2, material.getCaracterisitca().getVolumePeso());
			ps.setString(3, material.getCaracterisitca().getDescricao());
			ps.setString(4, material.getCaracterisitca().getCategoria());
			ps.setString(5, material.getCaracterisitca().getLinha());
			ps.setInt(6, material.getSku());
			if(ps.executeUpdate() > 0){
				retorno = true;
			}
		}
		criaCon.fecharConexao();
		return retorno;
	}
	
	public boolean atualizar(Material material) throws ClassNotFoundException, SQLException{
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "UPDATE CARACTERISTICA SET (COR, VOLUME_PESO, DESCRICAO, CATEGORIA, LINHA) " +
					 "VALUES (?, ?, ?, ?, ?)" +
					 "WHERE SKU = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!material.getCaracterisitca().equals(null)){
			ps.setString(1, material.getCaracterisitca().getCor());
			ps.setString(2, material.getCaracterisitca().getVolumePeso());
			ps.setString(3, material.getCaracterisitca().getDescricao());
			ps.setString(4, material.getCaracterisitca().getCategoria());
			ps.setString(5, material.getCaracterisitca().getLinha());
			ps.setInt(6, material.getSku());
			if(ps.executeUpdate() > 0){
				retorno = true;
			}
		}
		criaCon.fecharConexao();
		return retorno;
	}
	
	public Caracteristica pesquisar(Material material) throws ClassNotFoundException, SQLException{
		Caracteristica c = new Caracteristica();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_CARACTERISTICA, COR, VOLUME_PESO, DESCRICAO, CATEGORIA, LINHA, SKU) " +
					 "FROM CARACTERISTICA " +
					 "WHERE SKU = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!material.equals(null)){
			ps.setInt(1, material.getSku());
			ResultSet rs = ps.executeQuery();
			c.setId(rs.getInt("ID_CARACTERISTICA"));
			c.setCor(rs.getString("COR"));
			c.setVolumePeso(rs.getString("VOLUME_PESO"));
			c.setDescricao(rs.getString("DESCRICAO"));
			c.setCategoria(rs.getString("CATEGORIA"));
			c.setLinha(rs.getString("LINHA"));
			c.setSku(rs.getInt("SKU"));
		}
		criaCon.fecharConexao();
		return c;
	}

}
package pkgUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CriaConexao {
	
	public Connection con = null;
	
	public Connection abrirConexao() throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PROJETO_POO", "root", "admin");
			return con;
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("com.mysql.jdbc.Driver não encontrada", e);
		}
	}
	
	public void fecharConexao() throws SQLException {
		if(!con.isClosed()){
			con.close();
		}
	}
}
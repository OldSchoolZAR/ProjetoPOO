package pkgDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import pkgEntity.Produto;
import pkgEntity.Valor;
import pkgInterface.ValorDAO;
import pkgUtil.CriaConexao;

public class ValorDAOImpl implements ValorDAO {

	public ValorDAOImpl() {
		super();
	}

	@Override
	public boolean inserir(Produto produto) throws ClassNotFoundException, SQLException {
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "INSERT INTO VALOR (DATA, PRECO_SUGERIDO, QTD_PONTO, PRODUTO_SKU) " +
					 "VALUES (?, ?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(sql);
		if (!produto.valor.isEmpty()){
			ps.setDate(1, new java.sql.Date(produto.getValor().getData().getTime()));
			ps.setDouble(2, produto.getValor().getPrecoSugerido());
			ps.setInt(3, produto.getValor().getQtdPonto());
			ps.setInt(4, produto.getSku());
			if(ps.executeUpdate() > 0){
				retorno = true;
			}
			criaCon.fecharConexao();
		}
		return retorno;
	}
	
	@Override
	public boolean atualizar(Produto produto) throws ClassNotFoundException, SQLException {
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "UPDATE VALOR SET (DATA, PRECO_SUGERIDO, QTD_PONTO, PRODUTO_SKU) " +
					 "VALUES (?, ?, ?, ?)" +
					 "WHERE ID_VALOR = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if (!produto.getValor().equals(null)){
			ps.setDate(1, new java.sql.Date(produto.getValor().getData().getTime()));
			ps.setDouble(2, produto.getValor().getPrecoSugerido());
			ps.setInt(3, produto.getValor().getQtdPonto());
			ps.setInt(4, produto.getSku());
			ps.setInt(5, produto.getValor().getId());
			if(ps.executeUpdate() > 0){
				retorno = true;
			}
			criaCon.fecharConexao();
		}
		return retorno;
	}

	@Override
	public LinkedList<Valor> pesquisarValor(Produto produto) throws ClassNotFoundException, SQLException {
		LinkedList<Valor> listaValor = new LinkedList<Valor>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (ID_VALOR, DATA, PRECO_SUGERIDO, QTD_PONTO, PRODUTO_SKU) " +
					 "FROM VALOR " +
					 "WHERE PRODUTO_SKU = ? " +
					 "ORDER BY DATA DESC;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!(produto.getSku() != 0)){
			ps.setInt(1, produto.getSku());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Valor vlr = new Valor();
				vlr.setId(rs.getInt("ID_VALOR"));
				vlr.setData(new java.util.Date(rs.getDate("DATA").getTime()));
				vlr.setPrecoSugerido(rs.getDouble("PRECO_SUGERIDO"));
				vlr.setQtdPonto(rs.getInt("QTD_PONTO"));
				vlr.produto.setSku(rs.getInt("PRODUTO_SKU"));
				listaValor.add(vlr);
			}
		}
		criaCon.fecharConexao();
		return listaValor;
	}

}
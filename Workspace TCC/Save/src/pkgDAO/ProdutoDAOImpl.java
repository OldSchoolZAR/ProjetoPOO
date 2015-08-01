package pkgDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import pkgEntity.Produto;
import pkgInterface.ProdutoDAO;
import pkgInterface.ValorDAO;
import pkgUtil.CriaConexao;

public class ProdutoDAOImpl implements ProdutoDAO {

	public ProdutoDAOImpl() {
		super();
	}
	
	private ValorDAO valorDAO;
	private CaracteristicaDAOImpl caracDAO;

	@Override
	public boolean inserir(Produto produto) throws ClassNotFoundException, SQLException {
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "INSERT INTO PRODUTO (SKU, QUANTIDADE, QUANTIDADE_BRINDE, NOME, FORNECEDOR_ID_FORNECEDOR) " +
					 "VALUES (?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(sql);
		if (!produto.equals(null)){
			ps.setInt(1, produto.getSku());
			ps.setInt(2, produto.getQuantidade());
			ps.setInt(3, produto.getQuantidadeBrinde());
			ps.setString(4, produto.getNome());
			ps.setInt(6, produto.getFornecedor().getId());
			if(ps.executeUpdate() > 0){
				retorno = true;
			}
			criaCon.fecharConexao();
			caracDAO.inserir(produto);
			valorDAO.inserir(produto);
		}
		return retorno;
	}
	
	@Override
	public boolean atualizar(Produto produto) throws ClassNotFoundException, SQLException {
		boolean retorno = false;
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "UPDATE PRODUTO SET (QUANTIDADE, QUANTIDADE_BRINDE, NOME, FORNECEDOR_ID_FORNECEDOR) " +
					 "VALUES (?, ?, ?, ?, ?)" +
					 "WHERE SKU = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		if (!produto.equals(null)){
			ps.setInt(1, produto.getQuantidade());
			ps.setInt(2, produto.getQuantidadeBrinde());
			ps.setString(3, produto.getNome());
			ps.setInt(5, produto.getFornecedor().getId());
			ps.setInt(6, produto.getSku());
			if(ps.executeUpdate() > 0){
				retorno = true;
			}
			criaCon.fecharConexao();
			caracDAO.atualizar(produto);
			valorDAO.atualizar(produto);
		}
		return retorno;
	}

	@Override
	public LinkedList<Produto> pesquisar(Produto produto) throws ClassNotFoundException, SQLException {
		LinkedList<Produto> listaProduto = new LinkedList<Produto>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (SKU, QUANTIDADE, QUANTIDADE_BRINDE, NOME, FORNECEDOR_ID_FORNECEDOR) " +
					 "FROM PRODUTO " +
					 "WHERE NOME LIKE ? " +
					 "ORDER BY NOME ASC;";
		PreparedStatement ps = con.prepareStatement(sql);
		if(!produto.equals(null)){
			ps.setString(1, "%" + produto.getNome() + "%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Produto pdt = new Produto();
				pdt.setSku(rs.getInt("SKU"));
				pdt.setQuantidade(rs.getInt("QUANTIDADE"));
				pdt.setQuantidadeBrinde(rs.getInt("QUANTIDADE_BRINDE"));
				pdt.setNome(rs.getString("NOME"));
				pdt.getFornecedor().setId(rs.getInt("FORNECEDOR_ID_FORNECEDOR"));
				listaProduto.add(pdt);
			}
		}
		criaCon.fecharConexao();
		for(Produto p: listaProduto){
			p.setValor(valorDAO.pesquisarValor(p));
			p.setCaracterisitca(caracDAO.pesquisar(p));
		}
		return listaProduto;
	}

	@Override
	public LinkedList<Produto> pesquisarTodos() throws ClassNotFoundException, SQLException {
		LinkedList<Produto> listaProduto = new LinkedList<Produto>();
		CriaConexao criaCon = new CriaConexao();
		Connection con = criaCon.abrirConexao();
		String sql = "SELECT (SKU, QUANTIDADE, QUANTIDADE_BRINDE, NOME, FORNECEDOR_ID_FORNECEDOR) " +
					 "FROM PRODUTO " +
					 "ORDER BY NOME ASC;";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Produto pdt = new Produto();
			pdt.setSku(rs.getInt("SKU"));
			pdt.setQuantidade(rs.getInt("QUANTIDADE"));
			pdt.setQuantidadeBrinde(rs.getInt("QUANTIDADE_BRINDE"));
			pdt.setNome(rs.getString("NOME"));
			pdt.getFornecedor().setId(rs.getInt("FORNECEDOR_ID_FORNECEDOR"));
			listaProduto.add(pdt);
		}
		criaCon.fecharConexao();
		for(Produto p: listaProduto){
			p.setValor(valorDAO.pesquisarValor(p));
			p.setCaracterisitca(caracDAO.pesquisar(p));
		}
		return listaProduto;
	}

}
package pkgInterface;

import java.sql.SQLException;
import java.util.LinkedList;

import pkgEntity.MaterialApoio;

public interface MApoioDAO {

	public boolean atualizar(MaterialApoio material) throws ClassNotFoundException, SQLException;

	public boolean inserir(MaterialApoio material) throws ClassNotFoundException, SQLException;

	public LinkedList<MaterialApoio> pesquisar(MaterialApoio material) throws ClassNotFoundException, SQLException;

	public LinkedList<MaterialApoio> pesquisarTodos() throws ClassNotFoundException, SQLException;

}
package br.senac.corcovado.model.dao;

import br.senac.corcovado.model.entity.Categoria;
import br.senac.corcovado.model.utils.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author wesley
 */
public class CategoriaDao {

    public static void inserir(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO CATEGORIA (nome, descricao, departamento_id) VALUES (?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setString(1, categoria.getNome());
            statement.setString(2, categoria.getDescricao());
            statement.setLong(3, categoria.getDepartamento().getId());

            statement.execute();
        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
    
    public static void atualizar(Categoria categoria) {

    }

    public static Categoria obter(long id) throws SQLException {
        String sql = "SELECT * FROM CATEGORIA WHERE (id=?)";

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

            result = statement.executeQuery();
            if (result.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(result.getLong("id"));
                categoria.setNome(result.getString("nome"));
                categoria.setDescricao(result.getString("descricao"));
                categoria.setDepartamento(DepartamentoDao.obter(result.getLong("departamento_id")));

                return categoria;
            }
        } finally {
            if (result != null && !result.isClosed()) {
                result.close();
            }
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }

        return null;
    }
    
    public static List<Categoria> listar() {
        return null;
    }

    public static void excluir(long id) {

    }
}

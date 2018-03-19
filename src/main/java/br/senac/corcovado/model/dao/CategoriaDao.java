package br.senac.corcovado.model.dao;

import br.senac.corcovado.model.entity.Categoria;
import br.senac.corcovado.model.utils.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author wesley
 */
public class CategoriaDao {

    public static void create(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO CATEGORIA (nome, descricao, departamento_id, created_at, updated_at, active) VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setString(1, categoria.getNome());
            statement.setString(2, categoria.getDescricao());
            statement.setLong(3, categoria.getDepartamento_id());
            statement.setTimestamp(4, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            statement.setTimestamp(5, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            statement.setBoolean(6, true);

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

    public static void update(Categoria categoria) {

    }

    public static Categoria search(long id) throws SQLException {
        String sql = "SELECT * FROM CATEGORIA WHERE (id=? AND active=?)";

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, id);
            statement.setBoolean(2, true);

            result = statement.executeQuery();
            if (result.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(result.getLong("id"));
                categoria.setNome(result.getString("nome"));
                categoria.setDescricao(result.getString("descricao"));
                categoria.setDepartamento_id(result.getLong("departamento_id"));
                categoria.setCreated_at(result.getTimestamp("created_at").getTime());
                categoria.setUpdated_at(result.getTimestamp("updated_at").getTime());
                categoria.setActive(result.getBoolean("active"));

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

    public static List<Categoria> list() throws SQLException {
        String sql = "SELECT * FROM CATEGORIA WHERE (active=?)";
        
        List<Categoria> categorias = null;

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setBoolean(1, true);

            result = statement.executeQuery();
            if (result.next()) {
                if(categorias == null) categorias = new LinkedList<>();
                Categoria categoria = new Categoria();
                categoria.setId(result.getLong("id"));
                categoria.setNome(result.getString("nome"));
                categoria.setDescricao(result.getString("descricao"));
                categoria.setDepartamento_id(result.getLong("departamento_id"));
                categoria.setCreated_at(result.getTimestamp("created_at").getTime());
                categoria.setUpdated_at(result.getTimestamp("updated_at").getTime());
                categoria.setActive(result.getBoolean("active"));

                categorias.add(categoria);
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

        return categorias;
    }

    public static void destroy(long id) {

    }
}

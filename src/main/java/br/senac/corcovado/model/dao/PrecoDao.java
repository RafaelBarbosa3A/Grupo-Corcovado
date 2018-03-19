package br.senac.corcovado.model.dao;

import br.senac.corcovado.model.entity.Preco;
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
public class PrecoDao {

    public static void create(Preco preco) throws SQLException {
        String sql = "INSERT INTO PRECO (produto_id, preco, nivel_id, created_at, updated_at, active) VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, preco.getProduto_id());
            statement.setDouble(2, preco.getPreco());
            statement.setString(3, preco.getNivel_id().toString());
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

    public static void update(Preco preco) {

    }

    public static Preco search(long id) throws SQLException {
        String sql = "SELECT * FROM PRECO WHERE (id=? AND active=?)";

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
                Preco preco = new Preco();
                preco.setId(result.getLong("id"));
                preco.setProduto_id(result.getLong("produto_id"));
                preco.setPreco(result.getDouble("preco"));
                //preco.setNivel(result.getString("nivel_id"));
                preco.setCreated_at(result.getTimestamp("created_at").getTime());
                preco.setUpdated_at(result.getTimestamp("updated_at").getTime());
                preco.setActive(result.getBoolean("active"));

                return preco;
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

    public static List<Preco> list() throws SQLException {
        String sql = "SELECT * FROM PRECO WHERE (active=?)";
        
        List<Preco> precos = null;

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);
            
            statement.setBoolean(1, true);

            result = statement.executeQuery();
            if (result.next()) {
                if(precos == null) precos = new LinkedList<>();
                Preco preco = new Preco();
                preco.setId(result.getLong("id"));
                preco.setProduto_id(result.getLong("produto_id"));
                preco.setPreco(result.getDouble("preco"));
                //preco.setNivel(result.getString("nivel_id"));
                preco.setCreated_at(result.getTimestamp("created_at").getTime());
                preco.setUpdated_at(result.getTimestamp("updated_at").getTime());
                preco.setActive(result.getBoolean("active"));

                precos.add(preco);
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

        return precos;
    }

    public static void destroy(long id) {

    }
}

package br.senac.corcovado.model.dao;

import br.senac.corcovado.model.entity.SAC;
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
public class SACDao {

    public static void create(SAC sac) throws SQLException {
        String sql = "INSERT INTO SAC (pessoa_id, contato, mensagem, status_mensagem, created_at, updated_at, active) VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, sac.getCliente_id());
            statement.setString(2, sac.getContato());
            statement.setString(3, sac.getMensagem());
            statement.setString(4, sac.getStatus_mensagem());
            statement.setTimestamp(5, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            statement.setTimestamp(6, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            statement.setBoolean(7, true);

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

    public static void update(SAC sac) {

    }

    public static SAC search(long id) throws SQLException {
        String sql = "SELECT * FROM PRODUTO WHERE (id=? AND active=?)";

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
                SAC sac = new SAC();
                sac.setId(result.getLong("id"));
                sac.setCliente_id(result.getLong("pessoa_id"));
                sac.setContato(result.getString("contato"));
                sac.setMensagem(result.getString("mensagem"));
                sac.setStatus_mensagem(result.getString("status_mensagem"));
                sac.setCreated_at(result.getTimestamp("created_at").getTime());
                sac.setUpdated_at(result.getTimestamp("updated_at").getTime());
                sac.setActive(result.getBoolean("active"));

                return sac;
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

    public static List<SAC> list() throws SQLException {
        String sql = "SELECT * FROM PRODUTO WHERE (active=?)";

        List<SAC> sacs = null;
        
        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setBoolean(1, true);

            result = statement.executeQuery();
            if (result.next()) {
                if(sacs == null) sacs = new LinkedList<>();
                SAC sac = new SAC();
                sac.setId(result.getLong("id"));
                sac.setCliente_id(result.getLong("pessoa_id"));
                sac.setContato(result.getString("contato"));
                sac.setMensagem(result.getString("mensagem"));
                sac.setStatus_mensagem(result.getString("status_mensagem"));
                sac.setCreated_at(result.getTimestamp("created_at").getTime());
                sac.setUpdated_at(result.getTimestamp("updated_at").getTime());
                sac.setActive(result.getBoolean("active"));

                sacs.add(sac);
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

        return sacs;
    }

    public static void destroy(long id) {

    }
}

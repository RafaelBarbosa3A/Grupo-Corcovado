package br.senac.corcovado.model.dao;

import br.senac.corcovado.model.entity.Resposta;
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
public class RespostaDao {

    public static void create(Resposta resposta) throws SQLException {
        String sql = "INSERT INTO RESPOSTA (sac_id, mensagem, pessoa_id, created_at, updated_at, active) VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, resposta.getSac_id());
            statement.setString(2, resposta.getMensagem());
            statement.setLong(3, resposta.getCliente_id());
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

    public static void update(Resposta resposta) {

    }

    public static Resposta search(long id) throws SQLException {
        String sql = "SELECT * FROM RESPOSTA WHERE (id=? AND active=?)";

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
                Resposta resposta = new Resposta();
                resposta.setId(result.getLong("id"));
                resposta.setSac_id(result.getLong("sac_id"));
                resposta.setMensagem(result.getString("mensagem"));
                resposta.setCliente_id(result.getLong("pessoa_id"));
                resposta.setCreated_at(result.getTimestamp("created_at").getTime());
                resposta.setUpdated_at(result.getTimestamp("updated_at").getTime());
                resposta.setActive(result.getBoolean("active"));

                return resposta;
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

    public static List<Resposta> list() throws SQLException {
        String sql = "SELECT * FROM RESPOSTA WHERE (active=?)";
        
        List<Resposta> respostas = null;

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);
            
            statement.setBoolean(1, true);

            result = statement.executeQuery();
            if (result.next()) {
                if(respostas == null) respostas = new LinkedList<>();
                Resposta resposta = new Resposta();
                resposta.setId(result.getLong("id"));
                resposta.setSac_id(result.getLong("sac_id"));
                resposta.setMensagem(result.getString("mensagem"));
                resposta.setCliente_id(result.getLong("pessoa_id"));
                resposta.setCreated_at(result.getTimestamp("created_at").getTime());
                resposta.setUpdated_at(result.getTimestamp("updated_at").getTime());
                resposta.setActive(result.getBoolean("active"));

                respostas.add(resposta);
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

        return respostas;
    }

    public static void destroy(long id) {

    }
}

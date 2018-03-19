package br.senac.corcovado.model.dao;

import br.senac.corcovado.model.entity.Reposicao;
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
public class ReposicaoDao {

    public static void create(Reposicao reposicao) throws SQLException {
        String sql = "INSERT INTO REPOSICAO (fornecedor, created_at, updated_at, active) VALUES (?, ?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setString(1, reposicao.getFornecedor());
            statement.setTimestamp(2, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            statement.setTimestamp(3, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            statement.setBoolean(4, true);

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

    public static void update(Reposicao reposicao) {

    }

    public static Reposicao search(long id) throws SQLException {
        String sql = "SELECT * FROM REPOSICAO WHERE (id=? AND active=?)";

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
                Reposicao reposicao = new Reposicao();
                reposicao.setId(result.getLong("id"));
                reposicao.setFornecedor(result.getString("fornecedor"));
                reposicao.setCreated_at(result.getTimestamp("created_at").getTime());
                reposicao.setUpdated_at(result.getTimestamp("updated_at").getTime());
                reposicao.setActive(result.getBoolean("active"));

                return reposicao;
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

    public static List<Reposicao> list() throws SQLException {
        String sql = "SELECT * FROM REPOSICAO WHERE (active=?)";
        
        List<Reposicao> reposicoes = null;

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);
            
            statement.setBoolean(1, true);

            result = statement.executeQuery();
            if (result.next()) {
                if(reposicoes == null) reposicoes = new LinkedList<>();
                Reposicao reposicao = new Reposicao();
                reposicao.setId(result.getLong("id"));
                reposicao.setFornecedor(result.getString("fornecedor"));
                reposicao.setCreated_at(result.getTimestamp("created_at").getTime());
                reposicao.setUpdated_at(result.getTimestamp("updated_at").getTime());
                reposicao.setActive(result.getBoolean("active"));

                reposicoes.add(reposicao);
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

        return reposicoes;
    }

    public static void destroy(long id) {

    }
}

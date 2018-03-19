package br.senac.corcovado.model.dao;

import br.senac.corcovado.model.entity.Departamento;
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
public class DepartamentoDao {

    public static void create(Departamento departamento) throws SQLException {
        String sql = "INSERT INTO DEPARTAMENTO (nome, descricao, created_at, updated_at, active) VALUES (?, ?, ?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setString(1, departamento.getNome());
            statement.setString(2, departamento.getDescricao());
            statement.setTimestamp(3, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            statement.setTimestamp(4, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            statement.setBoolean(5, true);

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

    public static void update(Departamento departamento) {

    }

    public static Departamento search(long id) throws SQLException {
        String sql = "SELECT * FROM DEPARTAMENTO WHERE (id=? AND active=?)";

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
                Departamento departamento = new Departamento();
                departamento.setId(result.getLong("id"));
                departamento.setNome(result.getString("nome"));
                departamento.setDescricao(result.getString("descricao"));
                departamento.setCreated_at(result.getTimestamp("created_at").getTime());
                departamento.setUpdated_at(result.getTimestamp("updated_at").getTime());
                departamento.setActive(result.getBoolean("active"));

                return departamento;
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

    public static List<Departamento> list() throws SQLException {
        String sql = "SELECT * FROM DEPARTAMENTO WHERE (active=?)";
        
        List<Departamento> departamentos = null;

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setBoolean(1, true);

            result = statement.executeQuery();
            if (result.next()) {
                if(departamentos == null) departamentos = new LinkedList<>();
                Departamento departamento = new Departamento();
                departamento.setId(result.getLong("id"));
                departamento.setNome(result.getString("nome"));
                departamento.setDescricao(result.getString("descricao"));
                departamento.setCreated_at(result.getTimestamp("created_at").getTime());
                departamento.setUpdated_at(result.getTimestamp("updated_at").getTime());
                departamento.setActive(result.getBoolean("active"));

                departamentos.add(departamento);
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

        return departamentos;
    }

    public static void destroy(long id) {

    }
}

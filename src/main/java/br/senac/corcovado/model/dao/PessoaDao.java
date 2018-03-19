package br.senac.corcovado.model.dao;

import br.senac.corcovado.model.entity.Pessoa;
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
public class PessoaDao {

    public static void create(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO PESSOA (nome, documento, login, senha, nivel_id, cargo_id, created_at, updated_at, active) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getDocumento());
            statement.setString(3, pessoa.getLogin());
            statement.setString(4, pessoa.getSenha());
            statement.setLong(5, pessoa.getNivel_id());
            statement.setLong(6, pessoa.getCargo_id());
            statement.setTimestamp(7, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            statement.setTimestamp(8, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            statement.setBoolean(9, true);

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

    public static void update(Pessoa pessoa) {

    }

    public static Pessoa search(long id) throws SQLException {
        String sql = "SELECT * FROM PESSOA WHERE (id=? AND active=?)";

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
                Pessoa pessoa = new Pessoa();
                pessoa.setId(result.getLong("id"));
                pessoa.setNome(result.getString("nome"));
                pessoa.setDocumento(result.getString("documento"));
                pessoa.setLogin(result.getString("login"));
                pessoa.setSenha(result.getString("senha"));
                pessoa.setNivel_id(result.getLong("nivel_id"));
                pessoa.setCargo_id(result.getLong("cargo_id"));
                pessoa.setCreated_at(result.getTimestamp("created_at").getTime());
                pessoa.setUpdated_at(result.getTimestamp("updated_at").getTime());
                pessoa.setActive(result.getBoolean("active"));

                return pessoa;
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

    public static List<Pessoa> list() throws SQLException {
        String sql = "SELECT * FROM PESSOA WHERE (active=?)";
        
        List<Pessoa> pessoas = null;

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);
            
            statement.setBoolean(1, true);

            result = statement.executeQuery();
            if (result.next()) {
                if(pessoas == null) pessoas = new LinkedList<>();
                Pessoa pessoa = new Pessoa();
                pessoa.setId(result.getLong("id"));
                pessoa.setNome(result.getString("nome"));
                pessoa.setDocumento(result.getString("documento"));
                pessoa.setLogin(result.getString("login"));
                pessoa.setSenha(result.getString("senha"));
                pessoa.setNivel_id(result.getLong("nivel_id"));
                pessoa.setCargo_id(result.getLong("cargo_id"));
                pessoa.setCreated_at(result.getTimestamp("created_at").getTime());
                pessoa.setUpdated_at(result.getTimestamp("updated_at").getTime());
                pessoa.setActive(result.getBoolean("active"));

                pessoas.add(pessoa);
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

        return pessoas;
    }

    public static void destroy(long id) {

    }
}

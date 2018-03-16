package br.com.corcovado.dao;

import br.com.corcovado.model.Pessoa;
import br.com.corcovado.utils.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author wesley
 */
public class Pessoa_Dao {

    public static void inserir(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO PESSOA (nome, documento, login, senha, nivel_id, cargo_id) VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getDocumento());
            statement.setString(3, pessoa.getLogin());
            statement.setString(4, pessoa.getSenha());
            statement.setString(5, pessoa.getNivel().toString());
            statement.setString(6, pessoa.getCargo().toString());

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

    public static void atualizar(Pessoa pessoa) {

    }

    public static Pessoa obter(long id) throws SQLException {
        String sql = "SELECT * FROM PESSOA WHERE (id=?)";

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

            result = statement.executeQuery();
            if (result.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(result.getLong("id"));
                pessoa.setNome(result.getString("nome"));
                pessoa.setDocumento(result.getString("documento"));
                pessoa.setLogin(result.getString("login"));
                pessoa.setSenha(result.getString("senha"));
                //pessoa.setNivel(result.getString("nivel_id"));
                //pessoa.setCargo(result.getString("cargo_id"));                

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

    public static List<Pessoa> listar() {
        return null;
    }

    public static void excluir(long id) {

    }
}

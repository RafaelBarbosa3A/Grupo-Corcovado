package br.com.corcovado.dao;

import br.com.corcovado.model.Departamento;
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
public class Departamento_Dao {
    public static void inserir(Departamento departamento) throws SQLException {
        String sql = "INSERT INTO DEPARTAMENTO (nome, descricao) VALUES (?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setString(1, departamento.getNome());
            statement.setString(2, departamento.getDescricao());            

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
    
    public static void atualizar(Departamento departamento) {

    }

    public static Departamento obter(long id) throws SQLException {
        String sql = "SELECT * FROM DEPARTAMENTO WHERE (id=?)";

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

            result = statement.executeQuery();
            if (result.next()) {
                Departamento departamento = new Departamento();
                departamento.setId(result.getLong("id"));
                departamento.setNome(result.getString("nome"));
                departamento.setDescricao(result.getString("descricao"));                

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
    
    public static List<Departamento> listar() {
        return null;
    }

    public static void excluir(long id) {

    }
}

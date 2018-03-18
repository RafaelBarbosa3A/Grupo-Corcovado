package br.senac.corcovado.model.dao;

import br.senac.corcovado.model.entity.Reposicao;
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
public class ReposicaoDao {
    public static void inserir(Reposicao reposicao) throws SQLException {
        String sql = "INSERT INTO REPOSICAO (fornecedor) VALUES (?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setString(1, reposicao.getFornecedor());            

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

    public static void atualizar(Reposicao reposicao) {

    }

    public static Reposicao obter(long id) throws SQLException {
        String sql = "SELECT * FROM REPOSICAO WHERE (id=?)";

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

            result = statement.executeQuery();
            if (result.next()) {
                Reposicao reposicao = new Reposicao();
                reposicao.setId(result.getLong("id"));
                reposicao.setFornecedor(result.getString("fornecedor"));                

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

    public static List<Reposicao> listar() {
        return null;
    }

    public static void excluir(long id) {

    }
}

package br.senac.corcovado.model.dao;

import br.senac.corcovado.model.entity.Produto_Reposicao;
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
public class Produto_Reposicao_Dao {

    public static void inserir(Produto_Reposicao pr) throws SQLException {
        String sql = "INSERT INTO PRODUTO_REPOSICAO (reposicao_id, produto_id, quantidade) VALUES (?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, pr.getReposicao().getId());
            statement.setLong(2, pr.getProduto().getId());
            statement.setInt(3, pr.getQuantidade());            

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

    public static void atualizar(Produto_Reposicao pr) {

    }

    public static Produto_Reposicao obter(long id) throws SQLException {
        String sql = "SELECT * FROM PRODUTO_REPOSICAO WHERE (id=?)";

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

            result = statement.executeQuery();
            if (result.next()) {
                Produto_Reposicao pr = new Produto_Reposicao();
                pr.setId(result.getLong("id"));
                pr.setReposicao(Reposicao_Dao.obter(result.getLong("reposicao_id")));
                pr.setProduto(Produto_Dao.obter(result.getLong("produto_id")));
                pr.setQuantidade(result.getInt("quantidade"));                

                return pr;
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

    public static List<Produto_Reposicao> listar() {
        return null;
    }

    public static void excluir(long id) {

    }
}

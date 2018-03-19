package br.senac.corcovado.model.dao;

import br.senac.corcovado.model.entity.Produto_Reposicao;
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
public class ProdutoReposicaoDao {

    public static void create(Produto_Reposicao pr) throws SQLException {
        String sql = "INSERT INTO PRODUTO_REPOSICAO (reposicao_id, produto_id, quantidade, created_at, updated_at, active) VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, pr.getReposicao_id());
            statement.setLong(2, pr.getProduto_id());
            statement.setInt(3, pr.getQuantidade());
            statement.setTimestamp(8, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            statement.setTimestamp(9, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            statement.setBoolean(10, true);

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

    public static void update(Produto_Reposicao pr) {

    }

    public static Produto_Reposicao search(long id) throws SQLException {
        String sql = "SELECT * FROM PRODUTO_REPOSICAO WHERE (id=? AND active=?)";

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
                Produto_Reposicao pr = new Produto_Reposicao();
                pr.setId(result.getLong("id"));
                pr.setReposicao_id(result.getLong("reposicao_id"));
                pr.setProduto_id(result.getLong("produto_id"));
                pr.setQuantidade(result.getInt("quantidade"));
                pr.setCreated_at(result.getTimestamp("created_at").getTime());
                pr.setUpdated_at(result.getTimestamp("updated_at").getTime());
                pr.setActive(result.getBoolean("active"));

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

    public static List<Produto_Reposicao> list() throws SQLException {
        String sql = "SELECT * FROM PRODUTO_REPOSICAO WHERE (id=? AND active=?)";
        
        List<Produto_Reposicao> prs = null;

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);
            
            statement.setBoolean(1, true);

            result = statement.executeQuery();
            if (result.next()) {
                if(prs == null) prs = new LinkedList<>();
                Produto_Reposicao pr = new Produto_Reposicao();
                pr.setId(result.getLong("id"));
                pr.setReposicao_id(result.getLong("reposicao_id"));
                pr.setProduto_id(result.getLong("produto_id"));
                pr.setQuantidade(result.getInt("quantidade"));
                pr.setCreated_at(result.getTimestamp("created_at").getTime());
                pr.setUpdated_at(result.getTimestamp("updated_at").getTime());
                pr.setActive(result.getBoolean("active"));

                prs.add(pr);
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

        return prs;
    }

    public static void destroy(long id) {

    }
}

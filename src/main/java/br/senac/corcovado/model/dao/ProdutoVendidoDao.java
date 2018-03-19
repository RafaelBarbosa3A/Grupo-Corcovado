package br.senac.corcovado.model.dao;

import br.senac.corcovado.model.entity.Produto_Vendido;
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
public class ProdutoVendidoDao {

    public static void create(Produto_Vendido pv) throws SQLException {
        String sql = "INSERT INTO PRODUTO_VENDIDO (produto_id, venda_id, quantidade, preco_total, created_at, updated_at, active) VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, pv.getProduto_id());
            statement.setLong(2, pv.getVenda_id());
            statement.setInt(3, pv.getQuantidade());
            statement.setDouble(4, pv.getPreco_total());
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

    public static void update(Produto_Vendido pv) {

    }

    public static Produto_Vendido search(long id) throws SQLException {
        String sql = "SELECT * FROM PRODUTO_VENDIDO WHERE (id=? AND active=?)";

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
                Produto_Vendido pv = new Produto_Vendido();
                pv.setId(result.getLong("id"));
                pv.setProduto_id(result.getLong("produto_id"));
                pv.setVenda_id(result.getLong("venda_id"));
                pv.setQuantidade(result.getInt("quantidade"));
                pv.setPreco_total(result.getDouble("preco_total"));
                pv.setCreated_at(result.getTimestamp("created_at").getTime());
                pv.setUpdated_at(result.getTimestamp("updated_at").getTime());
                pv.setActive(result.getBoolean("active"));

                return pv;
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

    public static List<Produto_Vendido> list() throws SQLException {
        String sql = "SELECT * FROM PRODUTO_VENDIDO WHERE (active=?)";

        List<Produto_Vendido> pvs = null;

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setBoolean(1, true);

            result = statement.executeQuery();
            if (result.next()) {
                if (pvs == null) {
                    pvs = new LinkedList<>();
                }
                Produto_Vendido pv = new Produto_Vendido();
                pv.setId(result.getLong("id"));
                pv.setProduto_id(result.getLong("produto_id"));
                pv.setVenda_id(result.getLong("venda_id"));
                pv.setQuantidade(result.getInt("quantidade"));
                pv.setPreco_total(result.getDouble("preco_total"));
                pv.setCreated_at(result.getTimestamp("created_at").getTime());
                pv.setUpdated_at(result.getTimestamp("updated_at").getTime());
                pv.setActive(result.getBoolean("active"));

                pvs.add(pv);
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

        return pvs;
    }

    public static void destroy(long id) {

    }
}

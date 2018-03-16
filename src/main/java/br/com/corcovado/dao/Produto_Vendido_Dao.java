package br.com.corcovado.dao;

import br.com.corcovado.model.Produto_Vendido;
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
public class Produto_Vendido_Dao {
    public static void inserir(Produto_Vendido pv) throws SQLException {
        String sql = "INSERT INTO PRODUTO_VENDIDO (produto_id, venda_id, quantidade, preco_total) VALUES (?, ?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, pv.getProduto().getId());
            statement.setLong(2, pv.getVenda().getId());
            statement.setInt(3, pv.getQuantidade());
            statement.setBigDecimal(4, pv.getPreco_total());            

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

    public static void atualizar(Produto_Vendido pv) {

    }

    public static Produto_Vendido obter(long id) throws SQLException {
        String sql = "SELECT * FROM PRODUTO_VENDIDO WHERE (id=?)";

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

            result = statement.executeQuery();
            if (result.next()) {
                Produto_Vendido pv = new Produto_Vendido();
                pv.setId(result.getLong("id"));
                pv.setProduto(Produto_Dao.obter(result.getLong("produto_id")));
                pv.setVenda(Venda_Dao.obter(result.getLong("venda_id")));
                pv.setQuantidade(result.getInt("quantidade"));
                pv.setPreco_total(result.getBigDecimal("preco_total"));                

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

    public static List<Produto_Vendido> listar() {
        return null;
    }

    public static void excluir(long id) {

    }
}

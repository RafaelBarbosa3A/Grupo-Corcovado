package br.senac.corcovado.model.dao;

import br.senac.corcovado.model.entity.Produto;
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
 * @author wesle
 */
public class ProdutoDao {

    public static void create(Produto produto) throws SQLException {
        String sql = "INSERT INTO PRODUTO (nome, descricao, fabricante, codigo, estoque, reservado, categoria_id, created_at, updated_at, active) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getDescricao());
            statement.setString(3, produto.getFabricante());
            statement.setString(4, produto.getCodigo());
            statement.setInt(5, produto.getEstoque());
            statement.setInt(6, produto.getReservado());
            statement.setLong(7, produto.getCategoria_id());
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

    public static void update(Produto produto) {

    }

    public static Produto search(long id) throws SQLException {
        String sql = "SELECT * FROM PRODUTO WHERE (id=? AND active=?)";

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
                Produto produto = new Produto();
                produto.setId(result.getLong("id"));
                produto.setNome(result.getString("nome"));
                produto.setDescricao(result.getString("descricao"));
                produto.setFabricante(result.getString("fabricante"));
                produto.setCodigo(result.getString("codigo"));
                produto.setEstoque(result.getInt("estoque"));
                produto.setReservado(result.getInt("reservado"));
                produto.setCategoria_id(result.getLong("categoria_id"));
                produto.setCreated_at(result.getTimestamp("created_at").getTime());
                produto.setUpdated_at(result.getTimestamp("updated_at").getTime());
                produto.setActive(result.getBoolean("active"));

                return produto;
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

    public static List<Produto> list() throws SQLException {
        String sql = "SELECT * FROM PRODUTO WHERE (active=?)";
        
        List<Produto> produtos = null;

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);
            
            statement.setBoolean(1, true);

            result = statement.executeQuery();
            if (result.next()) {
                if(produtos == null) produtos = new LinkedList<>();
                Produto produto = new Produto();
                produto.setId(result.getLong("id"));
                produto.setNome(result.getString("nome"));
                produto.setDescricao(result.getString("descricao"));
                produto.setFabricante(result.getString("fabricante"));
                produto.setCodigo(result.getString("codigo"));
                produto.setEstoque(result.getInt("estoque"));
                produto.setReservado(result.getInt("reservado"));
                produto.setCategoria_id(result.getLong("categoria_id"));
                produto.setCreated_at(result.getTimestamp("created_at").getTime());
                produto.setUpdated_at(result.getTimestamp("updated_at").getTime());
                produto.setActive(result.getBoolean("active"));

                produtos.add(produto);
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

        return produtos;
    }

    public static void destroy(long id) {

    }
}

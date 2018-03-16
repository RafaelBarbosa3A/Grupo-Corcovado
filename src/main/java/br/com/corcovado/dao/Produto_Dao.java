package br.com.corcovado.dao;

import br.com.corcovado.model.Produto;
import br.com.corcovado.utils.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author wesle
 */
public class Produto_Dao {

    public static void inserir(Produto produto) throws SQLException {
        String sql = "INSERT INTO PRODUTO (nome, descricao, fabricante, codigo, estoque, reservado, categoria_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

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
            statement.setLong(7, produto.getCategoria().getId());

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

    public static void atualizar(Produto produto) {

    }

    public static Produto obter(long id) throws SQLException {
        String sql = "SELECT * FROM PRODUTO WHERE (id=?)";

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

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
                produto.setCategoria(Categoria_Dao.obter(result.getLong("categoria_id")));

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

    public static List<Produto> listar() {
        return null;
    }

    public static void excluir(long id) {

    }
}

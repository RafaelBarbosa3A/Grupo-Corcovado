package br.senac.corcovado.model.dao;

import br.senac.corcovado.model.entity.Preco;
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
public class PrecoDao {

    public static void inserir(Preco preco) throws SQLException {
        String sql = "INSERT INTO PRECO (produto_id, preco, nivel_id) VALUES (?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, preco.getProduto().getId());
            statement.setBigDecimal(2, preco.getPreco());
            statement.setString(3, preco.getNivel().toString());

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

    public static void atualizar(Preco preco) {

    }

    public static Preco obter(long id) throws SQLException {
        String sql = "SELECT * FROM PRECO WHERE (id=?)";

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

            result = statement.executeQuery();
            if (result.next()) {
                Preco preco = new Preco();
                preco.setId(result.getLong("id"));
                preco.setProduto(ProdutoDao.obter(result.getLong("produto_id")));
                preco.setPreco(result.getBigDecimal("preco"));
                //preco.setNivel(result.getString("nivel_id"));                

                return preco;
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

    public static List<Preco> listar() {
        return null;
    }

    public static void excluir(long id) {

    }
}

package br.com.corcovado.dao;

import br.com.corcovado.model.Desconto;
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
public class Desconto_Dao {

    public static void inserir(Desconto desconto) throws SQLException {
        String sql = "INSERT INTO DESCONTO (desconto, percentual, cod_cupom) VALUES (?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setString(1, desconto.getDesconto());
            statement.setDouble(2, desconto.getPercentual());
            statement.setString(2, desconto.getCod_cupom());

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

    public static void atualizar(Desconto desconto) {

    }

    public static Desconto obter(long id) throws SQLException {
        String sql = "SELECT * FROM DESCONTO WHERE (id=?)";

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

            result = statement.executeQuery();
            if (result.next()) {
                Desconto desconto = new Desconto();
                desconto.setId(result.getLong("id"));
                desconto.setDesconto(result.getString("desconto"));
                desconto.setPercentual(result.getDouble("percentual"));
                desconto.setCod_cupom(result.getString("cod_cupom"));

                return desconto;
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

    public static List<Desconto> listar() {
        return null;
    }

    public static void excluir(long id) {

    }
}

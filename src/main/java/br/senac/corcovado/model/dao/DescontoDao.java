package br.senac.corcovado.model.dao;

import br.senac.corcovado.model.entity.Desconto;
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
public class DescontoDao {

    public static void create(Desconto desconto) throws SQLException {
        String sql = "INSERT INTO DESCONTO (desconto, percentual, cod_cupom, created_at, updated_at, active) VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setString(1, desconto.getDesconto());
            statement.setDouble(2, desconto.getPercentual());
            statement.setString(3, desconto.getCod_cupom());
            statement.setTimestamp(4, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            statement.setTimestamp(5, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            statement.setBoolean(6, true);

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

    public static void update(Desconto desconto) {

    }

    public static Desconto search(long id) throws SQLException {
        String sql = "SELECT * FROM DESCONTO WHERE (id=? AND active=?)";

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
                Desconto desconto = new Desconto();
                desconto.setId(result.getLong("id"));
                desconto.setDesconto(result.getString("desconto"));
                desconto.setPercentual(result.getDouble("percentual"));
                desconto.setCod_cupom(result.getString("cod_cupom"));
                desconto.setCreated_at(result.getTimestamp("created_at").getTime());
                desconto.setUpdated_at(result.getTimestamp("updated_at").getTime());
                desconto.setActive(result.getBoolean("active"));

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

    public static List<Desconto> list() throws SQLException {
        String sql = "SELECT * FROM DESCONTO WHERE (active=?)";
        
        List<Desconto> descontos = null;

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setBoolean(1, true);

            result = statement.executeQuery();
            if (result.next()) {
                if(descontos == null) descontos = new LinkedList<>();
                Desconto desconto = new Desconto();
                desconto.setId(result.getLong("id"));
                desconto.setDesconto(result.getString("desconto"));
                desconto.setPercentual(result.getDouble("percentual"));
                desconto.setCod_cupom(result.getString("cod_cupom"));
                desconto.setCreated_at(result.getTimestamp("created_at").getTime());
                desconto.setUpdated_at(result.getTimestamp("updated_at").getTime());
                desconto.setActive(result.getBoolean("active"));

                descontos.add(desconto);
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

        return descontos;
    }

    public static void destroy(long id) {

    }
}

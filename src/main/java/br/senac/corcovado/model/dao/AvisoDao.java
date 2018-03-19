package br.senac.corcovado.model.dao;

import br.senac.corcovado.model.entity.Aviso;
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
public class AvisoDao {

    public static void create(Aviso aviso) throws SQLException {
        String sql = "INSERT INTO AVISO (produto_id, cliente_id, created_at, updated_at, active) VALUES (?, ?, ?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, aviso.getProduto_id());
            statement.setLong(2, aviso.getCliente_id());
            statement.setTimestamp(3, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            statement.setTimestamp(4, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            statement.setBoolean(5, true);

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

    public static void uptade(Aviso aviso) {

    }

    public static Aviso search(long id) throws SQLException {
        String sql = "SELECT * FROM AVISO WHERE (id=? AND active=?)";

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
                Aviso aviso = new Aviso();
                aviso.setId(result.getLong("id"));
                aviso.setProduto_id(result.getLong("produto_id"));
                aviso.setCliente_id(result.getLong("cliente_id"));
                aviso.setCreated_at(result.getTimestamp("created_at").getTime());
                aviso.setUpdated_at(result.getTimestamp("updated_at").getTime());
                aviso.setActive(result.getBoolean("active"));

                return aviso;
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

    public static List<Aviso> list() throws SQLException {
        String sql = "SELECT * FROM AVISO WHERE (active=?)";
        
        List<Aviso> avisos = null;

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);
            
            statement.setBoolean(1, true);

            result = statement.executeQuery();
            if (result.next()) {
                if(avisos == null) avisos = new LinkedList<>();
                Aviso aviso = new Aviso();
                aviso.setId(result.getLong("id"));
                aviso.setProduto_id(result.getLong("produto_id"));
                aviso.setCliente_id(result.getLong("cliente_id"));
                aviso.setCreated_at(result.getTimestamp("created_at").getTime());
                aviso.setUpdated_at(result.getTimestamp("updated_at").getTime());
                aviso.setActive(result.getBoolean("active"));

                avisos.add(aviso);
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

        return avisos;
    }

    public static void destroy(long id) {
    }
}

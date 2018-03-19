package br.senac.corcovado.model.dao;

import br.senac.corcovado.model.entity.Venda;
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
public class VendaDao {

    public static void create(Venda venda) throws SQLException {
        String sql = "INSERT INTO VENDA (cliente_id, endereco_id, desconto_id, total, status_id, pagamento, comprovante, prazo_entrega, codigo_rastreamento, created_at, updated_at, active) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, venda.getCliente_id());
            statement.setLong(2, venda.getEndereco_id());
            statement.setLong(3, venda.getDesconto_id());
            statement.setDouble(4, venda.getTotal());
            statement.setLong(5, venda.getStatus_id());
            statement.setString(6, venda.getPagamento());
            statement.setString(7, venda.getComprovante());
            statement.setString(8, venda.getPrazo_entrega());
            statement.setString(9, venda.getCodigo_rastreamento());
            statement.setTimestamp(10, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            statement.setTimestamp(11, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            statement.setBoolean(12, true);

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

    public static void update(Venda venda) {

    }

    public static Venda search(long id) throws SQLException {
        String sql = "SELECT * FROM VENDA WHERE (id=? and active=?)";

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
                Venda venda = new Venda();
                venda.setId(result.getLong("id"));
                venda.setCliente_id(result.getLong("cliente_id"));
                venda.setEndereco_id(result.getLong("endereco_id"));
                venda.setDesconto_id(result.getLong("desconto_id"));
                venda.setTotal(result.getDouble("total"));
                //venda.setStatus(result.getString("status_id"));
                venda.setPagamento(result.getString("pagamento"));
                venda.setComprovante(result.getString("comprovante"));
                venda.setPrazo_entrega(result.getString("prazo_entrega"));
                venda.setCodigo_rastreamento(result.getString("codigo_rastreamento"));
                venda.setCreated_at(result.getTimestamp("created_at").getTime());
                venda.setUpdated_at(result.getTimestamp("updated_at").getTime());
                venda.setActive(result.getBoolean("active"));

                return venda;
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

    public static List<Venda> list() throws SQLException {
        String sql = "SELECT * FROM VENDA WHERE (active=?)";
        
        List<Venda> vendas = null;

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);
            
            statement.setBoolean(1, true);

            result = statement.executeQuery();
            if (result.next()) {
                if(vendas == null) vendas = new LinkedList<>();
                Venda venda = new Venda();
                venda.setId(result.getLong("id"));
                venda.setCliente_id(result.getLong("cliente_id"));
                venda.setEndereco_id(result.getLong("endereco_id"));
                venda.setDesconto_id(result.getLong("desconto_id"));
                venda.setTotal(result.getDouble("total"));
                //venda.setStatus(result.getString("status_id"));
                venda.setPagamento(result.getString("pagamento"));
                venda.setComprovante(result.getString("comprovante"));
                venda.setPrazo_entrega(result.getString("prazo_entrega"));
                venda.setCodigo_rastreamento(result.getString("codigo_rastreamento"));
                venda.setCreated_at(result.getTimestamp("created_at").getTime());
                venda.setUpdated_at(result.getTimestamp("updated_at").getTime());
                venda.setActive(result.getBoolean("active"));

                vendas.add(venda);
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

        return vendas;
    }

    public static void destroy(long id) {

    }
}

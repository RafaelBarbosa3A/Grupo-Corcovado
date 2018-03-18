package br.senac.corcovado.model.dao;

import br.senac.corcovado.model.entity.Venda;
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
public class Venda_Dao {

    public static void inserir(Venda venda) throws SQLException {
        String sql = "INSERT INTO VENDA (cliente_id, endereco_id, desconto_id, total, status_id, pagamento, comprovante, prazo_entrega, codigo_rastreamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, venda.getCliente().getId());
            statement.setLong(2, venda.getEndereco().getId());
            statement.setLong(3, venda.getDesconto().getId());
            statement.setBigDecimal(4, venda.getTotal());
            statement.setString(5, venda.getStatus().toString());
            statement.setString(6, venda.getPagamento());
            statement.setString(7, venda.getComprovante());
            statement.setString(8, venda.getPrazo_entrega());
            statement.setString(9, venda.getCodigo_rastreamento());            

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

    public static void atualizar(Venda venda) {

    }

    public static Venda obter(long id) throws SQLException {
        String sql = "SELECT * FROM VENDA WHERE (id=?)";

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

            result = statement.executeQuery();
            if (result.next()) {
                Venda venda = new Venda();
                venda.setId(result.getLong("id"));
                venda.setCliente(Pessoa_Dao.obter(result.getLong("cliente_id")));
                venda.setEndereco(Endereco_Dao.obter(result.getLong("endereco_id")));
                venda.setDesconto(Desconto_Dao.obter(result.getLong("desconto_id")));
                venda.setTotal(result.getBigDecimal("total"));
                //venda.setStatus(result.getString("status_id"));
                venda.setPagamento(result.getString("pagamento"));
                venda.setComprovante(result.getString("comprovante"));
                venda.setPrazo_entrega(result.getString("prazo_entrega"));
                venda.setCodigo_rastreamento(result.getString("codigo_rastreamento"));

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

    public static List<Venda> listar() {
        return null;
    }

    public static void excluir(long id) {

    }
}

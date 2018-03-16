package br.com.corcovado.dao;

import br.com.corcovado.model.SAC;
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
public class SAC_Dao {
    public static void inserir(SAC sac) throws SQLException {
        String sql = "INSERT INTO SAC (pessoa_id, contato, mensagem, status_mensagem) VALUES (?, ?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, sac.getCliente().getId());
            statement.setString(2, sac.getContato());
            statement.setString(3, sac.getMensagem());
            statement.setString(4, sac.getStatus_mensagem());            

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

    public static void atualizar(SAC sac) {

    }

    public static SAC obter(long id) throws SQLException {
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
                SAC sac = new SAC();
                sac.setId(result.getLong("id"));
                sac.setCliente(Pessoa_Dao.obter(result.getLong("pessoa_id")));
                sac.setContato(result.getString("contato"));
                sac.setMensagem(result.getString("mensagem"));
                sac.setStatus_mensagem(result.getString("status_mensagem"));                

                return sac;
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

    public static List<SAC> listar() {
        return null;
    }

    public static void excluir(long id) {

    }
}

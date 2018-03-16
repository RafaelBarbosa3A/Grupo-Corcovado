package br.com.corcovado.dao;

import br.com.corcovado.model.Aviso;
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
public class Aviso_Dao {

    public static void inserir(Aviso aviso) throws SQLException {
        String sql = "INSERT INTO AVISO (produto_id, cliente_id) VALUES (?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, aviso.getProduto().getId());
            statement.setLong(2, aviso.getCliente().getId());

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

    public static void atualizar(Aviso aviso) {

    }

    public static Aviso obter(long id) throws SQLException {
        String sql = "SELECT * FROM AVISO WHERE (id=?)";

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

            result = statement.executeQuery();
            if (result.next()) {
                Aviso aviso = new Aviso();
                aviso.setId(result.getLong("id"));
                aviso.setProduto(Produto_Dao.obter(result.getLong("produto_id")));
                aviso.setCliente(Pessoa_Dao.obter(result.getLong("cliente_id")));                

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

    public static List<Aviso> listar() {
        return null;
    }

    public static void excluir(long id) {

    }
}

package br.senac.corcovado.model.dao;

import br.senac.corcovado.model.entity.Resposta;
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
public class RespostaDao {
    public static void inserir(Resposta resposta) throws SQLException {
        String sql = "INSERT INTO RESPOSTA (sac_id, mensagem, pessoa_id) VALUES (?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, resposta.getSac().getId());
            statement.setString(2, resposta.getMensagem());
            statement.setLong(3, resposta.getCliente().getId());            

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

    public static void atualizar(Resposta resposta) {

    }

    public static Resposta obter(long id) throws SQLException {
        String sql = "SELECT * FROM RESPOSTA WHERE (id=?)";

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

            result = statement.executeQuery();
            if (result.next()) {
                Resposta resposta = new Resposta();
                resposta.setId(result.getLong("id"));
                resposta.setSac(SACDao.obter(result.getLong("sac_id")));
                resposta.setMensagem(result.getString("mensagem"));
                resposta.setCliente(PessoaDao.obter(result.getLong("pessoa_id")));                

                return resposta;
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

    public static List<Resposta> listar() {
        return null;
    }

    public static void excluir(long id) {

    }
}

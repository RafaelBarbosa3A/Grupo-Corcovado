package br.senac.corcovado.model.dao;

import br.senac.corcovado.model.entity.Endereco;
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
public class EnderecoDao {

    public static void inserir(Endereco endereco) throws SQLException {
        String sql = "INSERT INTO ENDERECO (cliente_id, rua, numero, bairro, cidade, estado, cep, complemento, principal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);
            
            statement.setString(1, endereco.getRua());
            statement.setInt(2, endereco.getNumero());
            statement.setString(3, endereco.getBairro());
            statement.setString(4, endereco.getCidade());
            statement.setString(5, endereco.getEstado());
            statement.setString(6, endereco.getEstado());
            statement.setString(7, endereco.getCep());
            statement.setString(8, endereco.getComplemento());
            statement.setString(9, endereco.getPrincipal());

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

    public static void atualizar(Endereco endereco) {

    }

    public static Endereco obter(long id) throws SQLException {
        String sql = "SELECT * FROM ENDERECO WHERE (id=?)";

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

            result = statement.executeQuery();
            if (result.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(result.getLong("id"));
                endereco.setCliente(PessoaDao.obter(result.getLong("cliente_id")));
                endereco.setRua(result.getString("rua"));
                endereco.setNumero(result.getInt("numero"));
                endereco.setBairro(result.getString("bairro"));
                endereco.setCidade(result.getString("cidade"));
                endereco.setEstado(result.getString("estado"));
                endereco.setCep(result.getString("cep"));
                endereco.setComplemento(result.getString("complemento"));
                endereco.setPrincipal(result.getString("principal"));

                return endereco;
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

    public static List<Endereco> listar() {
        return null;
    }

    public static void excluir(long id) {

    }
}

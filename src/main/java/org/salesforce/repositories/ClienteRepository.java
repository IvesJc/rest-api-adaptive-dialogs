package org.salesforce.repositories;

import org.salesforce.infrastructure.OracleDbConfiguration;
import org.salesforce.models.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ClienteRepository {

    public OracleDbConfiguration dbConfig;

    public ClienteRepository() {
        dbConfig = new OracleDbConfiguration();
    }

    public List<Cliente> getClientes() {

        List<Cliente> lista = new ArrayList<>();

        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM CLIENTE ");
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                mapResultSetToCliente(rs, cliente);
                lista.add(cliente);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public Cliente getClienteById(int id) {
        Cliente cliente = null;

        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM CLIENTE WHERE clie_id = ?")) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                mapResultSetToCliente(rs, cliente);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cliente;
    }

    public int createCliente(Cliente cliente) {
        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO CLIENTE (" +
                         "clie_nome, clie_sobrenome, clie_email, clie_tipo, clie_idioma, clie_pais, " +
                     "clie_telefone)" +
                     " VALUES " +
                     "(?, ?, ?, ?, ?, ?, ?)")) {

            prepareStatementForClienteInsert(cliente, st);

            return st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }



    public void updateCliente(Cliente cliente) {
        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "UPDATE CLIENTE " +
                             "SET clie_nome = ?, clie_sobrenome = ?, clie_email = ?, clie_tipo = ?, " +
                             "clie_idioma = ?, clie_pais = ?, clie_telefone = ? " +
                             "WHERE clie_id = ?")) {

            prepareStatementForClienteInsert(cliente, st);
            st.setInt(8, cliente.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteById(int id) {

        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "DELETE FROM CLIENTE WHERE clie_id = ?")) {

            st.setInt(1, id);

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void mapResultSetToCliente(ResultSet rs, Cliente cliente) throws SQLException {
        cliente.setId(rs.getInt("clie_id"));
        cliente.setNome(rs.getString("clie_nome"));
        cliente.setSobrenome(rs.getString("clie_sobrenome"));
        cliente.setEmail(rs.getString("clie_email"));
        cliente.setTipo(rs.getString("clie_tipo"));
        cliente.setIdioma(rs.getString("clie_idioma"));
        cliente.setPais(rs.getString("clie_pais"));
        cliente.setTelefone(rs.getString("clie_telefone"));
    }

    private void prepareStatementForClienteInsert(Cliente cliente, PreparedStatement st) throws SQLException {
        st.setString(1, cliente.getNome());
        st.setString(2, cliente.getSobrenome());
        st.setString(3, cliente.getEmail());
        st.setString(4, cliente.getTipo());
        st.setString(5, cliente.getIdioma());
        st.setString(6, cliente.getPais());
        st.setString(7, cliente.getTelefone());
    }

}

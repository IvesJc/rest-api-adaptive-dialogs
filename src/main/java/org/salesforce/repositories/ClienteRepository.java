package org.salesforce.repositories;

import org.salesforce.dto.ClienteDTO;
import org.salesforce.models.Cliente;
import org.salesforce.models.Empresa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//JOIN EMPRESA ON " +
//        "EMPRESA.ID = CLIENTE.EMPRESA

public class ClienteRepository {

    public static final String URL_CONNECTION = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    public static final String USER = "rm553243";
    public static final String PASSWORD = "180600";

    public ClienteRepository() {
    }

    public List<Cliente> getClientes() {

        List<Cliente> lista = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("SELECT * FROM CLIENTE ");
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("clie_id"));
                cliente.setNome(rs.getString("clie_nome"));
                cliente.setSobrenome(rs.getString("clie_sobrenome"));
                cliente.setEmail(rs.getString("clie_email"));
                cliente.setTipo(rs.getString("clie_tipo"));
                cliente.setIdioma(rs.getString("clie_idioma"));
                cliente.setPais(rs.getString("clie_pais"));
                cliente.setTelefone(rs.getString("clie_telefone"));
                lista.add(cliente);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public Cliente getClienteById(int id) {
        Cliente cliente = null;

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("SELECT * FROM CLIENTE WHERE clie_id = ?")) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("clie_id"));
                cliente.setNome(rs.getString("clie_nome"));
                cliente.setSobrenome(rs.getString("clie_sobrenome"));
                cliente.setEmail(rs.getString("clie_email"));
                cliente.setTipo(rs.getString("clie_tipo"));
                cliente.setIdioma(rs.getString("clie_idioma"));
                cliente.setPais(rs.getString("clie_pais"));
                cliente.setTelefone(rs.getString("clie_telefone"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cliente;
    }

    public void createCliente(ClienteDTO clienteDTO) {
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("INSERT INTO CLIENTE (" +
                     "clie_nome, clie_sobrenome, clie_email, clie_tipo, clie_idioma, clie_pais, " +
                     "clie_telefone)" +
                     " VALUES " +
                     "(?, ?, ?, ?, ?, ?, ?)")) {

            st.setString(1, clienteDTO.nome());
            st.setString(2, clienteDTO.sobrenome());
            st.setString(3, clienteDTO.email());
            st.setString(4, clienteDTO.tipo());
            st.setString(5, clienteDTO.idioma());
            st.setString(6, clienteDTO.pais());
            st.setString(7, clienteDTO.telefone());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCliente(ClienteDTO clienteDTO) {
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement(
                     "UPDATE CLIENTE " +
                             "SET clie_nome = ?, clie_sobrenome = ?, clie_email = ?, clie_tipo = ?, " +
                             "clie_idioma = ?, clie_pais = ?, clie_telefone = ? " +
                             "WHERE clie_id = ?")) {

            st.setString(1, clienteDTO.nome());
            st.setString(2, clienteDTO.sobrenome());
            st.setString(3, clienteDTO.email());
            st.setString(4, clienteDTO.tipo());
            st.setString(5, clienteDTO.idioma());
            st.setString(6, clienteDTO.pais());
            st.setString(7, clienteDTO.telefone());
            st.setInt(8, clienteDTO.id());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteById(int id) {

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement(
                     "DELETE FROM CLIENTE WHERE clie_id = ?")) {

            st.setInt(1, id);

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}

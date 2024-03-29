package org.salesforce.repositories;

import org.salesforce.models.Cliente;
import org.salesforce.models.Empresa;
import org.salesforce.models.Recurso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecursoRepository {
    public static final String URL_CONNECTION = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    public static final String USER = "rm553243";
    public static final String PASSWORD = "180600";

    public RecursoRepository() {
    }

    public List<Recurso> getRecursos() {
        List<Recurso> lista = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("SELECT * FROM RECURSOS");
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Recurso recurso = new Recurso();
                recurso.setId(rs.getInt("recursos_id"));
                recurso.setNome(rs.getString("recursos_nome"));
                recurso.setNotasPreco(rs.getString("recursos_notas"));
                recurso.setDescricao(rs.getString("recursos_desc"));
                recurso.setCategoria(rs.getString("recursos_categ"));
                lista.add(recurso);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public Recurso getRecursoById(int id) {
        Recurso recurso = null;

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("SELECT * FROM RECURSO WHERE recursos_id = ?")) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                recurso = new Recurso();
                recurso.setId(rs.getInt("recursos_id"));
                recurso.setNome(rs.getString("recursos_nome"));
                recurso.setNotasPreco(rs.getString("recursos_notas"));
                recurso.setDescricao(rs.getString("recursos_desc"));
                recurso.setCategoria(rs.getString("recursos_categ"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return recurso;
    }

    public void createRecurso(Recurso recurso) {
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("INSERT INTO RECURSOS (" +
                     "recursos_nome, recursos_notas, recursos_desc, recursos_categ)" +
                     " VALUES " +
                     "(?, ?, ?, ?)")) {

            st.setObject(1, recurso.getNome());
            st.setString(2, recurso.getNotasPreco());
            st.setString(3, recurso.getDescricao());
            st.setString(4, recurso.getCategoria());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateRecurso(Recurso recurso) {
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement(
                     "UPDATE RECURSO " +
                             "SET recursos_nome = ?, recursos_notas = ?, recursos_desc = ?, recursos_categ = ?" +
                             "WHERE recursos_id = ?" )){

            st.setString(1, recurso.getNome());
            st.setString(2, recurso.getNotasPreco());
            st.setString(3, recurso.getDescricao());
            st.setString(4, recurso.getCategoria());
            st.setInt(5, recurso.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteById(int id){

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement(
                     "DELETE FROM RECURSOS WHERE recursos_id = ?" )){

            st.setInt(1, id);

            st.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}

package org.salesforce.repositories;

import org.salesforce.models.Cliente;
import org.salesforce.models.Empresa;
import org.salesforce.models.Recurso;
import org.salesforce.models.TipoPlano;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecursoRepository {
    public static final String URL_CONNECTION = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    public static final String USER = "rm553243";
    public static final String PASSWORD = "180600";

    public RecursoRepository() {
    }

    public List<Recurso> getRecurso() {
        List<Recurso> lista = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("SELECT * FROM RECURSO");
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Recurso recurso = new Recurso();
                recurso.setId(rs.getInt("recurso_id"));
                recurso.setNome(rs.getString("recurso_nome"));
                recurso.setNotasPreco(rs.getString("recurso_notas"));
                recurso.setDescricao(rs.getString("recurso_desc"));
                recurso.setCategoria(rs.getString("recurso_categ"));
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
             PreparedStatement st = connection.prepareStatement("SELECT * FROM RECURSO WHERE recurso_id = ?")) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                recurso = new Recurso();
                recurso.setId(rs.getInt("recurso_id"));
                recurso.setNome(rs.getString("recurso_nome"));
                recurso.setNotasPreco(rs.getString("recurso_notas"));
                recurso.setDescricao(rs.getString("recurso_desc"));
                recurso.setCategoria(rs.getString("recurso_categ"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return recurso;
    }

    public int createRecurso(Recurso recurso) {
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("INSERT INTO RECURSO (" +
                     "recurso_nome, recurso_notas, recurso_desc, recurso_categ)" +
                     " VALUES " +
                     "(?, ?, ?, ?)", new String[]{"empresa_id"})) {

            st.setString(1, recurso.getNome());
            st.setString(2, recurso.getNotasPreco());
            st.setString(3, recurso.getDescricao());
            st.setString(4, recurso.getCategoria());

            int result = st.executeUpdate();
            ResultSet resultSet = st.getGeneratedKeys();
            if (resultSet.next()) {
                recurso.setId(resultSet.getInt(1));
                if (recurso.getTipoPlanoId() != null) {
                    createRecursoTipoPlanoConnection(recurso);
                }
            }
            return result;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    private void createRecursoTipoPlanoConnection(Recurso recurso){
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("INSERT INTO TIPO_PLANO_RECURSO_POSSUI (" +
                     "FK_RECURSO_recurso_id, FK_TIPO_PLANO_tipo_plano_id )" +
                     " VALUES " +
                     "(?, ?)")) {

            st.setInt(1, recurso.getId());
            st.setInt(2, recurso.getTipoPlanoId());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRecurso(Recurso recurso) {
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement(
                     "UPDATE RECURSO " +
                             "SET recurso_nome = ?, recurso_notas = ?, recurso_desc = ?, recurso_categ = ?" +
                             "WHERE recurso_id = ?" )){

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
                     "DELETE FROM RECURSO WHERE recurso_id = ?" )){

            st.setInt(1, id);

            st.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}

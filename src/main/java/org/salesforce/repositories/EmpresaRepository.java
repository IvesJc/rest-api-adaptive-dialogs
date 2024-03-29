package org.salesforce.repositories;

import org.salesforce.models.Empresa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaRepository {

    public static final String URL_CONNECTION = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    public static final String USER = "rm553243";
    public static final String PASSWORD = "180600";

    public EmpresaRepository() {
    }

    public List<Empresa> getEmpresa() {
        List<Empresa> lista = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("SELECT * FROM EMPRESA");
//             PreparedStatement st = connection.prepareStatement("SELECT CLIENTE.CLIE_ID * FROM CLIENTE " +
//                     "INNER JOIN EMPRESA" +
//                     " ON " +
//                     "CLIENTE.CLIE_ID = EMPRESA.CLIENTE.FK_CLIENTE_id");
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(rs.getInt("empresa_id"));
                empresa.setNome(rs.getString("empresa_nome"));
                empresa.setTipoIndustria(rs.getString("empresa_tipo_industria"));
                empresa.setTamanho(rs.getString("empresa_tamanho"));
                empresa.setPaisSede(rs.getString("empresa_pais_sede"));
                lista.add(empresa);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public Empresa getEmpresaById(int id) {
        Empresa empresa = null;

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st =
                     connection.prepareStatement("SELECT * FROM EMPRESA WHERE empresa_id = ?")) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                empresa = new Empresa();
                empresa.setId(rs.getInt("empresa_id"));
                empresa.setNome(rs.getString("empresa_nome"));
                empresa.setTipoIndustria(rs.getString("empresa_tipo_industria"));
                empresa.setTamanho(rs.getString("empresa_tamanho"));
                empresa.setPaisSede(rs.getString("empresa_pais_sede"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return empresa;
    }

    public void createEmpresa(Empresa empresa) {
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("INSERT INTO EMPRESA (" +
                     "empresa_nome, " +
                     "empresa_tipo_industria, " +
                     "empresa_tamanho, " +
                     "empresa_pais_sede)  " +
                     " VALUES " +
                     "(?, ?, ?, ?)")) {

            st.setString(1, empresa.getNome());
            st.setString(2, empresa.getTipoIndustria());
            st.setString(3, empresa.getTamanho());
            st.setString(4, empresa.getPaisSede());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateEmpresa(Empresa empresa) {
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement(
                     "UPDATE EMPRESA " +
                             "SET " +
                             "empresa_nome = ?, " +
                             "empresa_tipo_industria = ?, " +
                             "empresa_tamanho = ?, " +
                             "empresa_pais_sede = ? " +
                             "WHERE empresa_id = ?" )){


            st.setString(1, empresa.getNome());
            st.setString(2, empresa.getTipoIndustria());
            st.setString(3, empresa.getTamanho());
            st.setString(4, empresa.getPaisSede());

            st.setInt(5, empresa.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int deleteById(int id){

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement(
                     "DELETE FROM EMPRESA WHERE empresa_id = ?" )){

            st.setInt(1, id);

            return st.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
}

package org.salesforce.repositories;

import org.salesforce.dto.EmpresaDTO;
import org.salesforce.models.Cliente;
import org.salesforce.models.Empresa;
import org.salesforce.models.Funcionario;
import org.salesforce.models.Produto;

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
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(rs.getInt("empresa_id"));
                empresa.setNome(rs.getString("empresa_nome"));
                empresa.setTipoIndustria(rs.getString("empresa_tipo_industria"));
                empresa.setTamanho(rs.getString("empresa_tamanho"));
                empresa.setRepresentante((Cliente) rs.getObject("empresa_tipo"));
                empresa.setPaisSede(rs.getString("empresa_pais_sede"));
                empresa.setProdutosContratados((Produto[]) rs.getObject("empresa_produtos_contratados"));
                empresa.setGestorSalesforce((Funcionario) rs.getObject("empresa_gestor_salesforce"));
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
                empresa.setRepresentante((Cliente) rs.getObject("empresa_tipo"));
                empresa.setPaisSede(rs.getString("empresa_pais_sede"));
                empresa.setProdutosContratados((Produto[]) rs.getObject("empresa_produtos_contratados"));
                empresa.setGestorSalesforce((Funcionario) rs.getObject("empresa_gestor_salesforce"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return empresa;
    }

    public void createEmpresa(Empresa empresa) {
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("INSERT INTO EMPRESA (" +
                     "empresa_nome, empresa_tipo_industria, empresa_tamanho, empresa_tipo, empresa_pais_sede, empresa_produtos_contratados, empresa_gestor_salesforce)" +
                     " VALUES " +
                     "(?, ?, ?, ?, ?, ?, ?)")) {

            st.setString(1, empresa.getNome());
            st.setString(2, empresa.getTipoIndustria());
            st.setString(3, empresa.getTamanho());
            st.setObject(4, empresa.getRepresentante());
            st.setString(5, empresa.getPaisSede());
            st.setObject(6, empresa.getProdutosContratados());
            st.setObject(7, empresa.getGestorSalesforce());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateEmpresa(EmpresaDTO empresaDTO) {
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement(
                     "UPDATE EMPRESA " +
                             "SET empresa_nome = ?, empresa_tipo_industria = ?, empresa_tamanho = ?, empresa_tipo = ?, " +
                             "empresa_pais_sede = ? " +
                             "WHERE empresa_id = ?" )){


            st.setString(1, empresaDTO.nome());
            st.setString(2, empresaDTO.tipoIndustria());
            st.setString(3, empresaDTO.tamanho());
            st.setObject(4, empresaDTO.clieId());
            st.setString(5, empresaDTO.paisSede());
            st.setInt(8, empresaDTO.id());

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

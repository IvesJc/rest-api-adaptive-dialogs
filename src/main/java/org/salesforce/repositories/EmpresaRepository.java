package org.salesforce.repositories;

import org.salesforce.infrastructure.OracleDbConfiguration;
import org.salesforce.models.Empresa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaRepository {

    public OracleDbConfiguration dbConfig;

    public EmpresaRepository() {
        dbConfig = new OracleDbConfiguration();
    }

    public List<Empresa> getEmpresa() {
        List<Empresa> lista = new ArrayList<>();

        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement("select * from empresa \n" +
                     "left join EMP_FUNC_ATENDE on empresa.empresa_id = EMP_FUNC_ATENDE.FK_EMPRESA_atende_func \n" +
                     "left join funcionario on funcionario.func_id = emp_func_atende.FK_FUNCIONARIO_atende_emp");
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Empresa empresa = new Empresa();
                mapResultSetToEmpresa(empresa, rs);
                lista.add(empresa);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


    public Empresa getEmpresaById(int id) {
        Empresa empresa = null;

        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st =
                     connection.prepareStatement("SELECT * FROM EMPRESA WHERE empresa_id = ?")) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                empresa = new Empresa();
                mapResultSetToEmpresa(empresa, rs);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return empresa;
    }

    public int createEmpresa(Empresa empresa) {
        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO EMPRESA (" +
                     "empresa_nome, " +
                     "empresa_tipo_industria, " +
                     "empresa_tamanho, " +
                     "empresa_pais_sede," +
                     "FK_CLIENTE_id)  " +
                     " VALUES " +
                     "(?, ?, ?, ?, ?)", new String[]{"empresa_id"})) {

            prepareStatementForEmpresaInsert(empresa, st);
            st.setInt(5, empresa.getClienteId());

            int result = st.executeUpdate();
            ResultSet resultSet = st.getGeneratedKeys();
            if (resultSet.next()){
                empresa.setId(resultSet.getInt(1));
                if (empresa.getFuncionarioId() != null) {
                    createEmpresaFuncionarioConnection(empresa);
                }
                if (empresa.getProdutoId() != null) {
                    createEmpresaProdutoConnection(empresa);
                }
            }
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    private void createEmpresaFuncionarioConnection(Empresa empresa){
        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO EMP_FUNC_ATENDE (" +
                     "FK_FUNCIONARIO_atende_emp, FK_EMPRESA_atende_func)" +
                     " VALUES " +
                     "(?, ?)")) {

            st.setInt(1, empresa.getFuncionarioId());
            st.setInt(2, empresa.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createEmpresaProdutoConnection(Empresa empresa){
        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO EMP_PROD_CONTRATA (" +
                     "FK_EMPRESA_atende_func, FK_FUNCIONARIO_atende_emp )" +
                     " VALUES " +
                     "(?, ?)")) {

            st.setInt(1, empresa.getId());
            st.setInt(2, empresa.getProdutoId());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateEmpresa(Empresa empresa) {
        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "UPDATE EMPRESA " +
                             "SET " +
                             "empresa_nome = ?, " +
                             "empresa_tipo_industria = ?, " +
                             "empresa_tamanho = ?, " +
                             "empresa_pais_sede = ? " +
                             "WHERE empresa_id = ?" )){


            prepareStatementForEmpresaInsert(empresa, st);
            st.setInt(5, empresa.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int deleteById(int id){

        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "DELETE FROM EMPRESA WHERE empresa_id = ?" )){

            st.setInt(1, id);

            return st.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    private static void mapResultSetToEmpresa(Empresa empresa, ResultSet rs) throws SQLException {
        empresa.setId(rs.getInt("empresa_id"));
        empresa.setNome(rs.getString("empresa_nome"));
        empresa.setTipoIndustria(rs.getString("empresa_tipo_industria"));
        empresa.setTamanho(rs.getString("empresa_tamanho"));
        empresa.setPaisSede(rs.getString("empresa_pais_sede"));
    }

    private static void prepareStatementForEmpresaInsert(Empresa empresa, PreparedStatement st) throws SQLException {
        st.setString(1, empresa.getNome());
        st.setString(2, empresa.getTipoIndustria());
        st.setString(3, empresa.getTamanho());
        st.setString(4, empresa.getPaisSede());
    }
}

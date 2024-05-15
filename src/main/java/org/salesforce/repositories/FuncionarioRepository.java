package org.salesforce.repositories;

import org.salesforce.infrastructure.OracleDbConfiguration;
import org.salesforce.models.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository {

    public OracleDbConfiguration dbConfig;

    public FuncionarioRepository() {
        dbConfig = new OracleDbConfiguration();
    }

    public List<Funcionario> getFuncionarios() {
        List<Funcionario> lista = new ArrayList<>();

        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM FUNCIONARIO");
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                mapResultSetToFuncionario(rs, funcionario);
                lista.add(funcionario);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public Funcionario getFuncionarioById(int id) {
        Funcionario funcionario = null;

        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st =
                     connection.prepareStatement("SELECT * FROM FUNCIONARIO WHERE func_id = ?")) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                funcionario = new Funcionario();
                mapResultSetToFuncionario(rs, funcionario);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return funcionario;
    }

    public int createFuncionario(Funcionario funcionario) {
        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO FUNCIONARIO (" +
                     "func_nome, func_sobrenome, func_cargo, func_email, func_telefone, func_salario)" +
                     " VALUES " +
                     "(?, ?, ?, ?, ?, ?)", new String[]{"func_id"})) {

            prepareStatementForFuncionarioInsert(funcionario, st);

            int result = st.executeUpdate();
            ResultSet resultSet = st.getGeneratedKeys();
            if (resultSet.next()){
                funcionario.setId(resultSet.getInt(1));
                if (funcionario.getEmpresaId() != null){
                    createFuncionarioEmpresaConnection(funcionario);
                }
            }
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    private void createFuncionarioEmpresaConnection(Funcionario funcionario) {
        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO EMP_FUNC_ATENDE (" +
                     "FK_FUNCIONARIO_atende_emp, FK_EMPRESA_atende_func )" +
                     " VALUES " +
                     "(?, ?)")) {

            st.setInt(1, funcionario.getId());
            st.setInt(2, funcionario.getEmpresaId());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFuncionario(Funcionario funcionario) {
        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "UPDATE FUNCIONARIO " +
                             "SET func_nome = ?, func_sobrenome = ?, func_cargo = ?, func_email = ?, func_telefone = ?, " +
                             "func_salario = ? " +
                             "WHERE func_id = ?")) {

            prepareStatementForFuncionarioInsert(funcionario, st);
            st.setInt(7, funcionario.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteById(int id) {

        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "DELETE FROM FUNCIONARIO WHERE func_id = ?")) {

            st.setInt(1, id);

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void mapResultSetToFuncionario(ResultSet rs, Funcionario funcionario) throws SQLException {
        funcionario.setId(rs.getInt("func_id"));
        funcionario.setNome(rs.getString("func_nome"));
        funcionario.setSobrenome(rs.getString("func_sobrenome"));
        funcionario.setCargo(rs.getString("func_cargo"));
        funcionario.setEmail(rs.getString("func_email"));
        funcionario.setTelefone(rs.getString("func_telefone"));
        funcionario.setSalario(rs.getDouble("func_salario"));
    }

    private void prepareStatementForFuncionarioInsert(Funcionario funcionario, PreparedStatement st) throws SQLException {
        st.setString(1, funcionario.getNome());
        st.setString(2, funcionario.getSobrenome());
        st.setString(3, funcionario.getCargo());
        st.setString(4, funcionario.getEmail());
        st.setString(5, funcionario.getTelefone());
        st.setDouble(6, funcionario.getSalario());
    }
}

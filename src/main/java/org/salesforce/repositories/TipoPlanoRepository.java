package org.salesforce.repositories;

import org.salesforce.models.Cliente;
import org.salesforce.models.Empresa;
import org.salesforce.models.Recurso;
import org.salesforce.models.TipoPlano;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoPlanoRepository {
    public static final String URL_CONNECTION = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    public static final String USER = "rm553243";
    public static final String PASSWORD = "180600";

    public TipoPlanoRepository() {
    }

    public List<TipoPlano> getTipoPlano() {
        List<TipoPlano> lista = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("SELECT * FROM TIPO_PLANO");
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                TipoPlano tipoPlano = new TipoPlano();
                tipoPlano.setId(rs.getInt("tipo_plano_id"));
                tipoPlano.setNome(rs.getString("tipo_plano_nome"));
                tipoPlano.setDescricao(rs.getString("tipo_plano_desc"));
                tipoPlano.setPreco(rs.getDouble("tipo_plano_preco"));
                tipoPlano.setTipoPreco(rs.getString("tipo_plano_tipo_preco"));
                tipoPlano.setNivelPlano(rs.getInt("tipo_plano_nivel_plano"));
                tipoPlano.setTesteGratisDisponivel(rs.getBoolean("TIPO_PLANO_TESTE_GRATIS_DISPONIVEL"));
                lista.add(tipoPlano);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public TipoPlano getTipoPlanoById(int id) {
        TipoPlano tipoPlano = null;

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st =
                     connection.prepareStatement("SELECT * FROM TIPO_PLANO WHERE tipo_plano_id = ?")) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                tipoPlano = new TipoPlano();
                tipoPlano.setId(rs.getInt("tipo_plano_id"));
                tipoPlano.setNome(rs.getString("tipo_plano_nome"));
                tipoPlano.setDescricao(rs.getString("tipo_plano_desc"));
                tipoPlano.setPreco(rs.getDouble("tipo_plano_preco"));
                tipoPlano.setTipoPreco(rs.getString("tipo_plano_tipo_preco"));
                tipoPlano.setNivelPlano(rs.getInt("tipo_plano_nivel_plano"));
                tipoPlano.setTesteGratisDisponivel(rs.getBoolean("TIPO_PLANO_TESTE_GRATIS_DISPONIVEL"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tipoPlano;
    }

    public void createTipoPlano(TipoPlano tipoPlano) {
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("INSERT INTO TIPO_PLANO (" +
                     "tipo_plano_nome, tipo_plano_desc, tipo_plano_preco, tipo_plano_tipo_preco, tipo_plano_nivel_plano, TIPO_PLANO_TESTE_GRATIS_DISPONIVEL)" +
                     " VALUES " +
                     "(?, ?, ?, ?, ?, ?)")) {

            st.setString(1, tipoPlano.getNome());
            st.setString(2, tipoPlano.getDescricao());
            st.setDouble(3, tipoPlano.getPreco());
            st.setString(4, tipoPlano.getTipoPreco());
            st.setInt(5, tipoPlano.getNivelPlano());
            st.setBoolean(6, tipoPlano.isTesteGratisDisponivel());

            int result = st.executeUpdate();
            ResultSet resultSet = st.getGeneratedKeys();
            if (resultSet.next()) {
                tipoPlano.setId(resultSet.getInt(1));
                if (tipoPlano.getTipoProdutoId() != null) {
                    createTipoPlanoTipoProdConnection(tipoPlano);
                }
                if (tipoPlano.getRecursosId() != null){
                    createTipoPlanoRecursoConnection(tipoPlano);
                }
            }
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    private void createTipoPlanoTipoProdConnection(TipoPlano tipoPlano){
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("INSERT INTO TIPO_PROD_TIPO_PLANO_TEM (" +
                     "FK_TIPO_PLANO_tipo_plano_id, FK_TIPO_PRODUTO_tipo_prod_id )" +
                     " VALUES " +
                     "(?, ?)")) {

            st.setInt(1, tipoPlano.getId());
            st.setInt(2, tipoPlano.getTipoProdutoId());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTipoPlanoRecursoConnection(TipoPlano tipoPlano){
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("INSERT INTO TIPO_PLANO_RECURSOS_POSSUI (" +
                     "FK_RECURSOS_recursos_id, FK_TIPO_PLANO_tipo_plano_id )" +
                     " VALUES " +
                     "(?, ?)")) {

            st.setInt(1, tipoPlano.getRecursosId());
            st.setInt(2, tipoPlano.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateTipoPlano(TipoPlano tipoPlano) {
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement(
                     "UPDATE TIPO_PLANO " +
                             "SET tipo_plano_nome = ?, tipo_plano_desc = ?, tipo_plano_preco = ?, tipo_plano_tipo_preco = ?, tipo_plano_nivel_plano = ?, TIPO_PLANO_TESTE_GRATIS_DISPONIVEL = ? " +
                             "WHERE tipo_plano_id = ?" )){

            st.setString(1, tipoPlano.getNome());
            st.setString(2, tipoPlano.getDescricao());
            st.setDouble(3, tipoPlano.getPreco());
            st.setString(4, tipoPlano.getTipoPreco());
            st.setInt(5, tipoPlano.getNivelPlano());
            st.setBoolean(6, tipoPlano.isTesteGratisDisponivel());
            st.setInt(7, tipoPlano.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteById(int id){

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement(
                     "DELETE FROM TIPO_PLANO WHERE tipo_plano_id = ?" )){

            st.setInt(1, id);

            st.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}

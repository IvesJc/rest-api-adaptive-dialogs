package org.salesforce.repositories;

import org.salesforce.models.Cliente;
import org.salesforce.models.Empresa;
import org.salesforce.models.PerguntasFrequentes;
import org.salesforce.models.TipoProduto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PerguntasFrequentesRepository {
    public static final String URL_CONNECTION = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    public static final String USER = "rm553243";
    public static final String PASSWORD = "180600";

    public PerguntasFrequentesRepository() {
    }

    public List<PerguntasFrequentes> getPerguntasFrequentes() {
        List<PerguntasFrequentes> lista = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("SELECT * FROM PERGUNTAS_FREQUENTES");
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                PerguntasFrequentes perguntasFrequentes = new PerguntasFrequentes();
                perguntasFrequentes.setId(rs.getInt("perg_id"));
                perguntasFrequentes.setPergunta(rs.getString("perg_perguntas"));
                perguntasFrequentes.setResposta(rs.getString("perg_respostas"));

                lista.add(perguntasFrequentes);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public PerguntasFrequentes getPergFreqById(int id) {
        PerguntasFrequentes perguntasFrequentes = null;

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st =
                     connection.prepareStatement("SELECT * FROM PERGUNTAS_FREQUENTES WHERE perg_id = ?")) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                perguntasFrequentes = new PerguntasFrequentes();
                perguntasFrequentes.setId(rs.getInt("perg_id"));
                perguntasFrequentes.setPergunta(rs.getString("perg_perguntas"));
                perguntasFrequentes.setResposta(rs.getString("perg_respostas"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return perguntasFrequentes;
    }

    public void createPergFreq(PerguntasFrequentes perguntasFrequentes) {
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("INSERT INTO PERGUNTAS_FREQUENTES (" +
                     "perg_perguntas, perg_respostas, FK_TIPO_PRODUTO_tipo_prod_id)" +
                     " VALUES " +
                     "(?, ?, ?)")) {

            st.setString(1, perguntasFrequentes.getPergunta());
            st.setString(2, perguntasFrequentes.getResposta());
            st.setInt(3, perguntasFrequentes.getTipoProdutoId());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updatePergFreq(PerguntasFrequentes perguntasFrequentes) {
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement(
                     "UPDATE PERGUNTAS_FREQUENTES " +
                             "SET perg_perguntas = ?, perg_respostas = ? " +
                             "WHERE perg_id = ?" )){

            st.setString(1, perguntasFrequentes.getPergunta());
            st.setString(2, perguntasFrequentes.getResposta());
            st.setInt(3, perguntasFrequentes.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteById(int id){

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement(
                     "DELETE FROM PERGUNTAS_FREQUENTES WHERE perg_id = ?" )){

            st.setInt(1, id);

            st.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}

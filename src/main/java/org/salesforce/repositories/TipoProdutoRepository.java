package org.salesforce.repositories;

import org.salesforce.models.Empresa;
import org.salesforce.models.TipoPlano;
import org.salesforce.models.TipoProduto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoProdutoRepository {
    public static final String URL_CONNECTION = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    public static final String USER = "rm553243";
    public static final String PASSWORD = "180600";

    public TipoProdutoRepository() {
    }

    public List<TipoProduto> getTipoProduto() {
        List<TipoProduto> lista = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("SELECT * FROM TIPO_PRODUTO");
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                TipoProduto tipoProduto = new TipoProduto();
                tipoProduto.setId(rs.getInt("tipo_prod_id"));
                tipoProduto.setNome(rs.getString("tipo_prod_nome"));
                tipoProduto.setDescricao(rs.getString("tipo_prod_desc"));
                tipoProduto.setAddOn(rs.getBoolean("tipo_prod_prod_add_on"));
                tipoProduto.setNomeGrupo(rs.getString("tipo_prod_nome_grupo"));
                lista.add(tipoProduto);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public TipoProduto getTipoProdutoById(int id) {
        TipoProduto tipoProduto = null;

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st =
                     connection.prepareStatement("SELECT * FROM TIPO_PRODUTO WHERE tipo_prod_id = ?")) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                tipoProduto = new TipoProduto();
                tipoProduto.setId(rs.getInt("tipo_prod_id"));
                tipoProduto.setNome(rs.getString("tipo_prod_nome"));
                tipoProduto.setDescricao(rs.getString("tipo_prod_desc"));
                tipoProduto.setAddOn(rs.getBoolean("tipo_prod_prod_add_on"));
                tipoProduto.setNomeGrupo(rs.getString("tipo_prod_nome_grupo"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tipoProduto;
    }

    public int createTipoProduto(TipoProduto tipoProduto) {
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("INSERT INTO TIPO_PRODUTO (" +
                     "tipo_prod_nome, tipo_prod_desc, tipo_prod_prod_add_on, tipo_prod_nome_grupo)" +
                     " VALUES " +
                     "(?, ?, ?, ?)", new String[]{"empresa_id"})) {

            st.setString(1, tipoProduto.getNome());
            st.setString(2, tipoProduto.getDescricao());
            st.setBoolean(3, tipoProduto.isAddOn());
            st.setString(4, tipoProduto.getNomeGrupo());

            int result = st.executeUpdate();
            ResultSet resultSet = st.getGeneratedKeys();
            if (resultSet.next()) {
                tipoProduto.setId(resultSet.getInt(1));
                if (tipoProduto.getTipoPlanoId() != null) {
                    createTipoProdTipoPlanoConnection(tipoProduto);
                }
            }
            return result;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    private void createTipoProdTipoPlanoConnection(TipoProduto tipoProduto){
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("INSERT INTO TIPO_PROD_TIPO_PLANO_TEM (" +
                     "FK_TIPO_PLANO_tipo_plano_id, FK_TIPO_PRODUTO_tipo_prod_id )" +
                     " VALUES " +
                     "(?, ?)")) {

            st.setInt(1, tipoProduto.getTipoPlanoId());
            st.setInt(2, tipoProduto.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTipoProduto(TipoProduto tipoProduto) {
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement(
                     "UPDATE TIPO_PRODUTO " +
                             "SET tipo_prod_nome = ?, tipo_prod_desc = ?,  tipo_prod_prod_add_on = ?, " +
                             "tipo_prod_nome_grupo = ? " +
                             "WHERE tipo_prod_id = ?" )){

            st.setString(1, tipoProduto.getNome());
            st.setString(2, tipoProduto.getDescricao());
            st.setBoolean(3, tipoProduto.isAddOn());
            st.setString(4, tipoProduto.getNomeGrupo());
            st.setInt(5, tipoProduto.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteById(int id){

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement(
                     "DELETE FROM TIPO_PRODUTO WHERE tipo_prod_id = ?" )){

            st.setInt(1, id);

            st.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}

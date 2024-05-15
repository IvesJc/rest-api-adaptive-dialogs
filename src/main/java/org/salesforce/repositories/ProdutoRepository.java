package org.salesforce.repositories;

import org.salesforce.infrastructure.OracleDbConfiguration;
import org.salesforce.models.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    public OracleDbConfiguration dbConfig;

    public ProdutoRepository() {
        dbConfig = new OracleDbConfiguration();
    }

    public List<Produto> getProd() {
        List<Produto> lista = new ArrayList<>();

        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM PRODUTO");
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto();
                mapResultSetToProduto(produto, rs);
                lista.add(produto);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public Produto getProdutoById(int id) {
        Produto produto = null;

        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM PRODUTO WHERE prod_id = ?")) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                produto = new Produto();
                mapResultSetToProduto(produto, rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return produto;
    }

    public int createProduto(Produto produto) {
        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO PRODUTO (" +
                     "prod_preco, prod_status," +
                     "prod_teste_gratis_ate, FK_TIPO_PRODUTO_tipo_prod_id) " +
                     " VALUES " +
                     "(?, ?, ?, ?)", new String[]{"empresa_id"})) {

            prepareStatementForProdutoInsert(produto, st);
            st.setInt(4, produto.getTipoProdutoId());

            int result = st.executeUpdate();
            ResultSet resultSet = st.getGeneratedKeys();
            if (resultSet.next()) {
                produto.setId(resultSet.getInt(1));
                if (produto.getEmpresaId() != null) {
                    createProdutoEmpresaConnection(produto);
                }
            }
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    private void createProdutoEmpresaConnection(Produto produto){
        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO EMP_PROD_CONTRATA (" +
                     "FK_EMPRESA_atende_func, FK_PRODUTO_prod_id )" +
                     " VALUES " +
                     "(?, ?)")) {

            st.setInt(1, produto.getEmpresaId());
            st.setInt(2, produto.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduto(Produto produto) {
        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "UPDATE PRODUTO " +
                             "SET prod_preco = ?, prod_status = ?,  " +
                             "prod_teste_gratis_ate = ? " +
                             "WHERE prod_id = ?" )){

            prepareStatementForProdutoInsert(produto, st);
            st.setInt(4, produto.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteById(int id){

        try (
            Connection connection = dbConfig.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "DELETE FROM PRODUTO WHERE prod_id = ?" )){

            st.setInt(1, id);

            st.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void mapResultSetToProduto(Produto produto, ResultSet rs) throws SQLException {
        produto.setId(rs.getInt("prod_id"));
        produto.setPreco(rs.getDouble("prod_preco"));
        produto.setStatus(rs.getString("prod_status"));
        produto.setTesteGratisAte(rs.getDate("prod_teste_gratis_ate"));
    }

    private static void prepareStatementForProdutoInsert(Produto produto, PreparedStatement st) throws SQLException {
        st.setDouble(1, produto.getPreco());
        st.setString(2, produto.getStatus());
        st.setDate(3, produto.getTesteGratisAte());
    }
}

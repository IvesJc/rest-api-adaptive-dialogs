package org.salesforce.repositories;

import org.salesforce.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    public static final String URL_CONNECTION = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    public static final String USER = "rm553243";
    public static final String PASSWORD = "180600";

    public ProdutoRepository() {
    }

    public List<Produto> getProd() {
        List<Produto> lista = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("SELECT * FROM PRODUTO");
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("prod_id"));
                produto.setEmpresa((Empresa) rs.getObject("prod_empresa"));
                produto.setPreco(rs.getDouble("prod_preco"));
                produto.setStatus(rs.getString("prod_status"));
                produto.setPlanoContratado((TipoPlano) rs.getObject("prod_plano_contratado"));
                produto.setTipoProduto((TipoProduto) rs.getObject("prod_tipo_produto"));
                produto.setTesteGratisAte(rs.getDate("prod_teste_gratis_ate"));
                lista.add(produto);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public Produto getProdutoById(int id) {
        Produto produto = null;

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("SELECT * FROM PRODUTO WHERE prod_id = ?")) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("prod_id"));
                produto.setEmpresa((Empresa) rs.getObject("prod_empresa"));
                produto.setPreco(rs.getDouble("prod_preco"));
                produto.setStatus(rs.getString("prod_status"));
                produto.setPlanoContratado((TipoPlano) rs.getObject("prod_plano_contratado"));
                produto.setTipoProduto((TipoProduto) rs.getObject("prod_tipo_produto"));
                produto.setTesteGratisAte(rs.getDate("prod_teste_gratis_ate"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return produto;
    }

    public void createProduto(Produto produto) {
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("INSERT INTO PRODUTO (" +
                     "prod_empresa, prod_preco, prod_status, prod_plano_contratado, prod_tipo_produto, " +
                     "prod_teste_gratis_ate) " +
                     " VALUES " +
                     "(?, ?, ?, ?, ?, ?)")) {

            st.setObject(1, produto.getEmpresa());
            st.setDouble(2, produto.getPreco());
            st.setString(3, produto.getStatus());
            st.setObject(4, produto.getPlanoContratado());
            st.setObject(5, produto.getTipoProduto());
            st.setDate(6, produto.getTesteGratisAte());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProduto(Produto produto) {
        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement(
                     "UPDATE PRODUTO " +
                             "SET prod_empresa = ?, prod_preco = ?, prod_status = ?, prod_plano_contratado = ?, " +
                             "prod_tipo_produto = ?, prod_teste_gratis_ate = ? " +
                             "WHERE prod_id = ?" )){

            st.setObject(1, produto.getEmpresa());
            st.setDouble(2, produto.getPreco());
            st.setString(3, produto.getStatus());
            st.setObject(4, produto.getPlanoContratado());
            st.setObject(5, produto.getTipoProduto());
            st.setDate(6, produto.getTesteGratisAte());
            st.setInt(7, produto.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteById(int id){

        try (Connection connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement(
                     "DELETE FROM PRODUTO WHERE prod_id = ?" )){

            st.setInt(1, id);

            st.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}

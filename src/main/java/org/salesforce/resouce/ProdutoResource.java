package org.salesforce.resouce;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.salesforce.Main;
import org.salesforce.models.Produto;
import org.salesforce.repositories.ProdutoRepository;

import java.util.List;

@Path("produto")
public class ProdutoResource {

    ProdutoRepository produtoRepository = new ProdutoRepository();

    @GET
    public Response getProduto(){
        List<Produto> produtoList = produtoRepository.getProd();
        if (produtoList.isEmpty()){
            Main.LOGGER.info("404 - Produto NOT FOUND");
            return Response.status(404).entity("Produto not found").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(produtoList).build();
    }


    @GET
    @Path("{id}")
    public Response getProdutoById(@PathParam("id") int id){
        Produto produto = produtoRepository.getProdutoById(id);
        if (produto == null){
            Main.LOGGER.info("404 - Produto NOT FOUND");
            return Response.status(404).entity("Produto não encontrado").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(produto).build();
    }

    @POST
    public Response createProduto(Produto produto){
        if (produto == null){
            Main.LOGGER.info("404 - Produto can not be null");
            return Response.status(404).entity("Produto não pode ser nula").build();
        }
        int result = produtoRepository.createProduto(produto);
        if (result == 0){
            return Response.status(400).entity("Falha ao criar Produto").build();
        }
        Main.LOGGER.info("[POST] - 201 - OK");
        return Response.status(201).entity(produto).build();
    }


    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduto(@PathParam("id") int id, Produto produto){
        produto.setId(id);

        Produto prod = produtoRepository.getProdutoById(produto.getId());
        if (prod != null){
            produtoRepository.updateProduto(produto);
            Main.LOGGER.info("[PUT] - 200 - OK");
            return Response.status(200).entity(produto).build();
        }else {
            Main.LOGGER.info("404 - Produto not found ");
            return Response.status(404).entity("Produto não registrado").build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteProduto(@PathParam("id") int id){
        Produto prod = produtoRepository.getProdutoById(id);
        if (prod != null){
            produtoRepository.deleteById(id);
            Main.LOGGER.info("[DELETE] - 204 - No content");
            return Response.status(204).entity(prod).build();
        }else {
            return Response.status(400).entity("Produto não registrado").build();
        }
    }

}

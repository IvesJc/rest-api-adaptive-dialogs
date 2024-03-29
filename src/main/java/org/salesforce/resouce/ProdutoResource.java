package org.salesforce.resouce;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.salesforce.models.Cliente;
import org.salesforce.models.Produto;
import org.salesforce.repositories.ClienteRepository;
import org.salesforce.repositories.ProdutoRepository;

import java.util.List;

@Path("produto")
public class ProdutoResource {

    ProdutoRepository produtoRepository = new ProdutoRepository();

    @GET
    public List<Produto> getProduto(){
        return produtoRepository.getProd();
    }

    @GET
    @Path("{id}")
    public Response getProdutoById(@PathParam("id") int id){
        Produto produto = produtoRepository.getProdutoById(id);
        if (produto == null){
            return Response.status(404).entity("Produto não encontrado").build();
        }
        return Response.status(200).entity(produto).build();
    }

    @POST
    public Response createProduto(Produto produto){
        if (produto == null){
            return Response.status(400).entity("Produto não pode ser nulo").build();
        }
        produtoRepository.createProduto(produto);
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
            return Response.status(200).entity(produto).build();
        }else {
            return Response.status(400).entity("Produto não registrado").build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteProduto(@PathParam("id") int id){
        Produto prod = produtoRepository.getProdutoById(id);
        if (prod != null){
            produtoRepository.deleteById(id);
            return Response.status(200).entity(prod).build();
        }else {
            return Response.status(400).entity("Produto não registrado").build();
        }
    }

}

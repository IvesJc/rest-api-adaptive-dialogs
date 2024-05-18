package org.salesforce.resouce;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.salesforce.Main;
import org.salesforce.models.TipoProduto;
import org.salesforce.repositories.TipoProdutoRepository;

import java.util.List;

@Path("tipo-produto")
public class TipoProdutoResource {

    TipoProdutoRepository tipoProdutoRepository = new TipoProdutoRepository();

    @GET
    public Response getTipoProduto(){
        List<TipoProduto> tipoProdutoList = tipoProdutoRepository.getTipoProduto();
        if (tipoProdutoList.isEmpty()){
            Main.LOGGER.info("404 - Tipo Produto NOT FOUND");
            return Response.status(404).entity("Tipo Produto not found").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(tipoProdutoList).build();
    }

    @GET
    @Path("{id}")
    public Response getTipoProdutoById(@PathParam("id") int id){
        TipoProduto tipoProduto = tipoProdutoRepository.getTipoProdutoById(id);
        if (tipoProduto == null){
            Main.LOGGER.info("404 - Tipo Produto NOT FOUND");
            return Response.status(404).entity("Tipo Produto não encontrado").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(tipoProduto).build();
    }
    @POST
    public Response createTipoProduto(TipoProduto tipoProduto){
        if (tipoProduto == null){
            Main.LOGGER.info("404 - Tipo Produto can not be null ");
            return Response.status(400).entity("Tipo Produto não pode ser nula").build();
        }
        int result = tipoProdutoRepository.createTipoProduto(tipoProduto);
        if (result == 0){
            return Response.status(400).entity("Falha ao criar Tipo Produto").build();
        }
        Main.LOGGER.info("[POST] - 201 - OK");
        return Response.status(201).entity(tipoProduto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTipoProduto(@PathParam("id") int id, TipoProduto tipoProduto){
        tipoProduto.setId(id);

        TipoProduto tipoProd = tipoProdutoRepository.getTipoProdutoById(tipoProduto.getId());
        if (tipoProd != null){
            tipoProdutoRepository.updateTipoProduto(tipoProduto);
            Main.LOGGER.info("[PUT] - 200 - OK");
            return Response.status(200).entity(tipoProduto).build();
        }else {
            Main.LOGGER.info("404 - Tipo Produto not found ");
            return Response.status(404).entity("Tipo Produto não registrado").build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteTipoProd(@PathParam("id") int id){
        TipoProduto tipoProd = tipoProdutoRepository.getTipoProdutoById(id);
        if (tipoProd != null){
            tipoProdutoRepository.deleteById(id);
            Main.LOGGER.info("[DELETE] - 204 - No content");
            return Response.status(204).entity(tipoProd).build();
        }else {
            return Response.status(400).entity("Tipo Produto não registrado").build();
        }
    }

}

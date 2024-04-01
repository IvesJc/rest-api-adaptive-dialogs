package org.salesforce.resouce;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.salesforce.models.Cliente;
import org.salesforce.models.TipoProduto;
import org.salesforce.repositories.ClienteRepository;
import org.salesforce.repositories.TipoPlanoRepository;
import org.salesforce.repositories.TipoProdutoRepository;

import java.util.List;

@Path("tipo-produto")
public class TipoProdutoResource {

    TipoProdutoRepository tipoProdutoRepository = new TipoProdutoRepository();

    @GET
    public List<TipoProduto> getTipoProduto(){
        return tipoProdutoRepository.getTipoProduto();
    }

    @GET
    @Path("{id}")
    public Response getTipoProdutoById(@PathParam("id") int id){
        TipoProduto tipoProduto = tipoProdutoRepository.getTipoProdutoById(id);
        if (tipoProduto == null){
            return Response.status(404).entity("Tipo Produto não encontrado").build();
        }
        return Response.status(200).entity(tipoProduto).build();
    }

    @POST
    public Response createTipoProduto(TipoProduto tipoProduto){
        if (tipoProduto == null){
            return Response.status(400).entity("Tipo Produto não pode ser nula").build();
        }
        int result = tipoProdutoRepository.createTipoProduto(tipoProduto);
        if (result == 0){
            return Response.status(400).entity("Falha ao criar Tipo Produto").build();
        }
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
            return Response.status(200).entity(tipoProduto).build();
        }else {
            return Response.status(400).entity("Tipo Produto não registrado").build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteTipoProd(@PathParam("id") int id){
        TipoProduto tipoProd = tipoProdutoRepository.getTipoProdutoById(id);
        if (tipoProd != null){
            tipoProdutoRepository.deleteById(id);
            return Response.status(200).entity(tipoProd).build();
        }else {
            return Response.status(400).entity("Tipo Produto não registrado").build();
        }
    }

}

package org.salesforce.resouce;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.salesforce.models.Cliente;
import org.salesforce.repositories.ClienteRepository;

import java.util.List;

@Path("cliente")
public class ClienteResource {

    ClienteRepository clienteRepository = new ClienteRepository();

    @GET
    public List<Cliente> getClientes(){
        return clienteRepository.getClientes();
    }

    @GET
    @Path("{id}")
    public Response getClienteById(@PathParam("id") int id){
        Cliente cliente = clienteRepository.getClienteById(id);
        if (cliente == null){
            return Response.status(404).entity("Cliente não encontrado").build();
        }
        return Response.status(200).entity(cliente).build();
    }

    @POST
    public Response createCliente(Cliente cliente){
        if (cliente == null){
            return Response.status(400).entity("Cliente não pode ser nulo").build();
        }
        clienteRepository.createCliente(cliente);
        return Response.status(201).entity(cliente).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCliente(@PathParam("id") int id, Cliente cliente){
        cliente.setId(id);

        Cliente clie = clienteRepository.getClienteById(id);
        if (clie != null){
            clienteRepository.updateCliente(cliente);
            return Response.status(200).entity(cliente).build();
        }else {
            return Response.status(400).entity("Cliente não registrado").build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteCliente(@PathParam("id") int id){
        Cliente clie = clienteRepository.getClienteById(id);
        if (clie != null){
            clienteRepository.deleteById(id);
            return Response.status(200).entity(clie).build();
        }else {
            return Response.status(400).entity("Cliente não registrado").build();
        }
    }


}

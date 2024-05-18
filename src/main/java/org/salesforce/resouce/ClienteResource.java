package org.salesforce.resouce;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.salesforce.Main;
import org.salesforce.models.Cliente;
import org.salesforce.repositories.ClienteRepository;

import java.util.List;

@Path("cliente")
public class ClienteResource {

    ClienteRepository clienteRepository = new ClienteRepository();

    @GET
    public Response getClientes(){
        List<Cliente> clienteList = clienteRepository.getClientes();
        if (clienteList.isEmpty()){
            Main.LOGGER.info("404 - Cliente NOT FOUND");
            return Response.status(404).entity("Cliente not found").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(clienteList).build();
    }

    @GET
    @Path("{id}")
    public Response getClienteById(@PathParam("id") int id){
        Cliente cliente = clienteRepository.getClienteById(id);
        if (cliente == null){
            Main.LOGGER.info("404 - Cliente NOT FOUND");
            return Response.status(404).entity("Cliente não encontrado").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(cliente).build();
    }

    @POST
    public Response createCliente(Cliente cliente){
        if (cliente == null){
            Main.LOGGER.info("404 - Cliente can not be null ");
            return Response.status(400).entity("Cliente não pode ser nula").build();
        }
        int result = clienteRepository.createCliente(cliente);
        if (result == 0){
            return Response.status(400).entity("Falha ao criar Cliente").build();
        }
        Main.LOGGER.info("[POST] - 201 - OK");
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
            Main.LOGGER.info("[PUT] - 200 - OK");
            return Response.status(200).entity(cliente).build();
        }else {
            Main.LOGGER.info("404 - Cliente not found ");
            return Response.status(404).entity("Cliente não registrado").build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteCliente(@PathParam("id") int id){
        Cliente clie = clienteRepository.getClienteById(id);
        if (clie != null){
            clienteRepository.deleteById(id);
            Main.LOGGER.info("[DELETE] - 204 - No content");
            return Response.status(204).entity(clie).build();
        }else {
            return Response.status(400).entity("Cliente não registrado").build();
        }
    }


}

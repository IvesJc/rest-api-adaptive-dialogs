package org.salesforce.resouce;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.salesforce.Main;
import org.salesforce.models.Funcionario;
import org.salesforce.repositories.FuncionarioRepository;

import java.util.List;

@Path("funcionario")
public class FuncionarioResource {

    FuncionarioRepository funcionarioRepository = new FuncionarioRepository();

    @GET
    public Response getFuncionario(){
        List<Funcionario> funcionarioList = funcionarioRepository.getFuncionarios();
        if (funcionarioList.isEmpty()){
            Main.LOGGER.info("404 - Funcionario NOT FOUND");
            return Response.status(404).entity("Funcionario not found").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(funcionarioList).build();
    }

    @GET
    @Path("{id}")
    public Response getFuncionarioById(@PathParam("id") int id){
        Funcionario funcionario = funcionarioRepository.getFuncionarioById(id);
        if (funcionario == null){
            Main.LOGGER.info("404 - Funcionario NOT FOUND");
            return Response.status(404).entity("Funcionário não encontrado").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(funcionario).build();
    }

    @POST
    public Response createFuncionario(Funcionario funcionario){
        if (funcionario == null){
            Main.LOGGER.info("404 - Funcionario can not be null ");
            return Response.status(404).entity("Funcionário não pode ser nula").build();
        }
        int result = funcionarioRepository.createFuncionario(funcionario);
        if (result == 0){
            return Response.status(400).entity("Falha ao criar Funcionário").build();
        }
        Main.LOGGER.info("[POST] - 201 - OK");
        return Response.status(201).entity(funcionario).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFuncionario(@PathParam("id") int id, Funcionario funcionario){
        funcionario.setId(id);
        Funcionario func = funcionarioRepository.getFuncionarioById(funcionario.getId());
        if (func != null){
            funcionarioRepository.updateFuncionario(funcionario);
            Main.LOGGER.info("[PUT] - 200 - OK");
            return Response.status(200).entity(funcionario).build();
        }else {
            Main.LOGGER.info("404 - Funcionario not found ");
            return Response.status(404).entity("Funcionário não registrado").build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteFuncionario(@PathParam("id") int id){
        Funcionario func = funcionarioRepository.getFuncionarioById(id);
        if (func != null){
            funcionarioRepository.deleteById(id);
            Main.LOGGER.info("[DELETE] - 204 - No content");
            return Response.status(204).entity(func).build();
        }else {
            return Response.status(400).entity("Funcionário não registrado").build();
        }
    }
}

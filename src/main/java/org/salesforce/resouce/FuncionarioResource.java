package org.salesforce.resouce;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.salesforce.models.Funcionario;
import org.salesforce.repositories.FuncionarioRepository;

import java.util.List;

@Path("funcionario")
public class FuncionarioResource {

    FuncionarioRepository funcionarioRepository = new FuncionarioRepository();

    @GET
    public List<Funcionario> getFuncionario(){
        return funcionarioRepository.getFuncionarios();
    }

    @GET
    @Path("{id}")
    public Response getFuncionarioById(@PathParam("id") int id){
        Funcionario funcionario = funcionarioRepository.getFuncionarioById(id);
        if (funcionario == null){
            return Response.status(404).entity("Funcionário não encontrado").build();
        }
        return Response.status(200).entity(funcionario).build();
    }

    @POST
    public Response createFuncionario(Funcionario funcionario){
        if (funcionario == null){
            return Response.status(400).entity("Funcionário não pode ser nula").build();
        }
        int result = funcionarioRepository.createFuncionario(funcionario);
        if (result == 0){
            return Response.status(400).entity("Falha ao criar Funcionário").build();
        }
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
            return Response.status(200).entity(funcionario).build();
        }else {
            return Response.status(400).entity("Funcionário não registrado").build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteFuncionario(@PathParam("id") int id){
        Funcionario func = funcionarioRepository.getFuncionarioById(id);
        if (func != null){
            funcionarioRepository.deleteById(id);
            return Response.status(200).entity(func).build();
        }else {
            return Response.status(400).entity("Funcionário não registrado").build();
        }
    }


}

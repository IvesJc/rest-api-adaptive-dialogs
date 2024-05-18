package org.salesforce.resouce;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.salesforce.Main;
import org.salesforce.models.Empresa;
import org.salesforce.repositories.EmpresaRepository;

import java.util.List;

@Path("empresa")
public class EmpresaResource {

    EmpresaRepository empresaRepository = new EmpresaRepository();

    @GET
    public Response getEmpresa(){
        List<Empresa> empresaList = empresaRepository.getEmpresa();
        if (empresaList.isEmpty()){
            Main.LOGGER.info("404 - Empresa NOT FOUND");
            return Response.status(404).entity("Empresa not found").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(empresaList).build();
    }

    @GET
    @Path("{id}")
    public Response getEmpresaById(@PathParam("id") int id){
        Empresa empresa = empresaRepository.getEmpresaById(id);
        if (empresa == null){
            Main.LOGGER.info("404 - Empresa NOT FOUND");
            return Response.status(404).entity("Empresa não encontrada").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(empresa).build();
    }

    @POST
    public Response createEmpresa(Empresa empresa){
        if (empresa == null){
            Main.LOGGER.info("404 - Empresa can not be null ");
            return Response.status(400).entity("Empresa não pode ser nula").build();
        }
        int result = empresaRepository.createEmpresa(empresa);
        if (result == 0){
            return Response.status(400).entity("Falha ao criar Empresa").build();
        }
        Main.LOGGER.info("[POST] - 201 - OK");
        return Response.status(201).entity(empresa).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmpresa(@PathParam("id") int id, Empresa empresa){
        empresa.setId(id);
        Empresa emp = empresaRepository.getEmpresaById(id);
        if (emp != null){
            empresaRepository.updateEmpresa(empresa);
            Main.LOGGER.info("[PUT] - 200 - OK");
            return Response.status(200).entity(empresa).build();
        }else {
            Main.LOGGER.info("404 - Empresa not found ");
            return Response.status(404).entity("Empresa não registrada").build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteEmpresa(@PathParam("id") int id){
        Empresa emp = empresaRepository.getEmpresaById(id);
        if (emp != null){
            empresaRepository.deleteById(id);
            Main.LOGGER.info("[DELETE] - 204 - No content");
            return Response.status(204).entity(emp).build();
        }else {
            return Response.status(400).entity("Empresa não registrada").build();
        }
    }
}

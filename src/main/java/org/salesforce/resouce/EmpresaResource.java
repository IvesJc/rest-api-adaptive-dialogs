package org.salesforce.resouce;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.salesforce.models.Cliente;
import org.salesforce.models.Empresa;
import org.salesforce.repositories.EmpresaRepository;

import java.util.List;

@Path("empresa")
public class EmpresaResource {

    EmpresaRepository empresaRepository = new EmpresaRepository();

    @GET
    public List<Empresa> getEmpresa(){
        return empresaRepository.getEmpresa();
    }

    @GET
    @Path("{id}")
    public Response getEmpresaById(@PathParam("id") int id){
        Empresa empresa = empresaRepository.getEmpresaById(id);
        if (empresa == null){
            return Response.status(404).entity("Empresa não encontrada").build();
        }
        return Response.status(200).entity(empresa).build();
    }

    @POST
    public Response createEmpresa(Empresa empresa){
        if (empresa == null){
            return Response.status(400).entity("Empresa não pode ser nula").build();
        }
        empresaRepository.createEmpresa(empresa);
        return Response.status(201).entity(empresa).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmpresa(@PathParam("id") int id, Empresa empresa){
        empresa.setId(id);

        Empresa emp = empresaRepository.getEmpresaById(empresa.getId());
        if (emp != null){
            empresaRepository.updateEmpresa(empresa);
            return Response.status(200).entity(empresa).build();
        }else {
            return Response.status(400).entity("Empresa não registrada").build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteEmpresa(@PathParam("id") int id){
        Empresa emp = empresaRepository.getEmpresaById(id);
        if (emp != null){
            empresaRepository.deleteById(id);
            return Response.status(200).entity(emp).build();
        }else {
            return Response.status(400).entity("Empresa não registrada").build();
        }
    }
}

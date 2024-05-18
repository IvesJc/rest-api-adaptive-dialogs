package org.salesforce.resouce;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.salesforce.Main;
import org.salesforce.models.PerguntasFrequentes;
import org.salesforce.repositories.PerguntasFrequentesRepository;

import java.util.List;

@Path("perguntas-frequentes")
public class PerguntasFrequentesResource {

    PerguntasFrequentesRepository perguntasFrequentesRepository = new PerguntasFrequentesRepository();

    @GET
    public Response getPergFreq() {
        List<PerguntasFrequentes> perguntasFrequentesList =
                perguntasFrequentesRepository.getPerguntasFrequentes();
        if (perguntasFrequentesList.isEmpty()){
            Main.LOGGER.info("404 - Pergunta NOT FOUND");
            return Response.status(404).entity("Pergunta not found").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(perguntasFrequentesList).build();
    }

    @GET
    @Path("{id}")
    public Response getPergFreqById(@PathParam("id") int id) {
        PerguntasFrequentes perguntasFrequentes = perguntasFrequentesRepository.getPergFreqById(id);
        if (perguntasFrequentes == null) {
            Main.LOGGER.info("404 - Pergunta NOT FOUND");
            return Response.status(404).entity("Pergunta não encontrado").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(perguntasFrequentes).build();
    }


    @POST
    public Response createPergFreq(PerguntasFrequentes perguntasFrequentes) {
        if (perguntasFrequentes == null){
            Main.LOGGER.info("404 - Pergunta can not be null ");
            return Response.status(404).entity("Perguntas Frequentes não pode ser nula").build();
        }
        int result = perguntasFrequentesRepository.createPergFreq(perguntasFrequentes);
        if (result == 0){
            return Response.status(400).entity("Falha ao criar Perguntas Frequentes").build();
        }
        Main.LOGGER.info("[POST] - 201 - OK");
        return Response.status(201).entity(perguntasFrequentes).build();
    }


    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePergFreq(@PathParam("id") int id, PerguntasFrequentes perguntasFrequentes) {
        perguntasFrequentes.setId(id);
        PerguntasFrequentes pergFreq =
                perguntasFrequentesRepository.getPergFreqById(perguntasFrequentes.getId());
        if (pergFreq != null) {
            perguntasFrequentesRepository.updatePergFreq(perguntasFrequentes);
            Main.LOGGER.info("[PUT] - 200 - OK");
            return Response.status(200).entity(perguntasFrequentes).build();
        } else {
            Main.LOGGER.info("404 - Pergunta not found ");
            return Response.status(404).entity("Pergunta Frequente não registrado").build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletePerguntaFrequente(@PathParam("id") int id) {
        PerguntasFrequentes pergFreq = perguntasFrequentesRepository.getPergFreqById(id);
        if (pergFreq != null) {
            perguntasFrequentesRepository.deleteById(id);
            Main.LOGGER.info("[DELETE] - 204 - No content");
            return Response.status(204).entity(pergFreq).build();
        } else {
            return Response.status(400).entity("Pergunta Frequente não registrado").build();
        }
    }
}


package org.salesforce.resouce;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.salesforce.models.Cliente;
import org.salesforce.models.PerguntasFrequentes;
import org.salesforce.repositories.ClienteRepository;
import org.salesforce.repositories.PerguntasFrequentesRepository;

import java.util.List;

@Path("perguntas_frequentes")
public class PerguntasFrequentesResource {

    PerguntasFrequentesRepository perguntasFrequentesRepository = new PerguntasFrequentesRepository();

    @GET
    public List<PerguntasFrequentes> getPergFreq() {
        return perguntasFrequentesRepository.getPerguntasFrequentes();
    }

    @GET
    @Path("{id}")
    public Response getPergFreqById(@PathParam("id") int id) {
        PerguntasFrequentes perguntasFrequentes = perguntasFrequentesRepository.getPergFreqById(id);
        if (perguntasFrequentes == null) {
            return Response.status(404).entity("Pergunta Frequente não encontrado").build();
        }
        return Response.status(200).entity(perguntasFrequentes).build();
    }

    @POST
    public Response createPergFreq(PerguntasFrequentes perguntasFrequentes) {
        if (perguntasFrequentes == null) {
            return Response.status(400).entity("Pergunta Frequente não pode ser nulo").build();
        }
        perguntasFrequentesRepository.createPergFreq(perguntasFrequentes);
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
            return Response.status(200).entity(perguntasFrequentes).build();
        } else {
            return Response.status(400).entity("Pergunta Frequente não registrado").build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletePerguntaFrequente(@PathParam("id") int id) {
        PerguntasFrequentes pergFreq = perguntasFrequentesRepository.getPergFreqById(id);
        if (pergFreq != null) {
            perguntasFrequentesRepository.deleteById(id);
            return Response.status(200).entity(pergFreq).build();
        } else {
            return Response.status(400).entity("Pergunta Frequente não registrado").build();
        }
    }
}


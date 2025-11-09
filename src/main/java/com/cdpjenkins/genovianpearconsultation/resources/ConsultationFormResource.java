package com.cdpjenkins.genovianpearconsultation.resources;


import com.cdpjenkins.genovianpearconsultation.api.Answer;
import com.cdpjenkins.genovianpearconsultation.api.ConsultationForm;
import com.cdpjenkins.genovianpearconsultation.core.ConsultationFormService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;

@Path("/consultations")
@Produces(MediaType.APPLICATION_JSON)
public class ConsultationFormResource {
    private final ConsultationFormService consultationFormService;

    public ConsultationFormResource(ConsultationFormService consultationFormService) {
        this.consultationFormService = consultationFormService;
    }

    @POST
    public Response createConsultationForm(ConsultationForm consultationForm) {
        ConsultationForm persistedConsultationForm = consultationFormService.createConsultationForm(consultationForm);

        return Response.created(uriFor(persistedConsultationForm)).entity(persistedConsultationForm).build();
    }

    @GET
    @Path("/{consultationId}")
    public ConsultationForm getConsultationForm(@PathParam("consultationId") int consultationId) {
        return consultationFormService.getConsultationForm(consultationId);
    }

    @POST
    @Path("/{consultationId}/questions/{questionId}/answer")
    public Response answerQuestion(
            @PathParam("consultationId") int consultationId,
            @PathParam("questionId") int questionId,
            Answer answer) {

        consultationFormService.answerQuestion(consultationId, questionId, answer);

        return Response.ok().build();
    }


    private static URI uriFor(ConsultationForm persistedConsultationForm) {
        return UriBuilder.fromResource(ConsultationFormResource.class).path(String.valueOf(persistedConsultationForm.getId())).build();
    }
}

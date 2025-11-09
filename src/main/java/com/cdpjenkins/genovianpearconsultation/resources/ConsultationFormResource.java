package com.cdpjenkins.genovianpearconsultation.resources;


import com.cdpjenkins.genovianpearconsultation.api.ConsultationForm;
import com.cdpjenkins.genovianpearconsultation.core.ConsultationFormService;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;
import java.util.concurrent.atomic.AtomicInteger;

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

        return Response.created(uriFor(persistedConsultationForm)).entity(consultationForm).build();
    }

    private static URI uriFor(ConsultationForm persistedConsultationForm) {
        return UriBuilder.fromResource(ConsultationFormResource.class).path(String.valueOf(persistedConsultationForm.getId())).build();
    }
}

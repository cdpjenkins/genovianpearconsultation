package com.cdpjenkins.genovianpearconsultation.resources;


import com.cdpjenkins.genovianpearconsultation.api.ConsultationForm;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;

@Path("/consultations")
@Produces(MediaType.APPLICATION_JSON)
public class ConsultationFormResource {
    public ConsultationFormResource() {}

    @POST
    public Response createConsultationForm() {
        ConsultationForm consultationForm = new ConsultationForm("genovian-pear");

        URI uri = UriBuilder.fromResource(ConsultationFormResource.class).build();

        return Response.created(uri).entity(consultationForm).build();
    }
}

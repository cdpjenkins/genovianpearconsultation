package com.cdpjenkins.genovianpearconsultation.resources;


import com.cdpjenkins.genovianpearconsultation.api.ConsultationForm;
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
    public ConsultationFormResource() {}

    AtomicInteger idAllocator = new AtomicInteger();

    @POST
    public Response createConsultationForm(ConsultationForm consultationForm) {
        int id = idAllocator.incrementAndGet();
        consultationForm.setId(id);

        URI uri = UriBuilder.fromResource(ConsultationFormResource.class).path(String.valueOf(id)).build();

        return Response.created(uri).entity(consultationForm).build();
    }
}

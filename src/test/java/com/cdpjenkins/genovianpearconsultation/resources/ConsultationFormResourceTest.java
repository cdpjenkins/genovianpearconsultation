package com.cdpjenkins.genovianpearconsultation.resources;

import com.cdpjenkins.genovianpearconsultation.api.ConsultationForm;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(DropwizardExtensionsSupport.class)
class ConsultationFormResourceTest {

    private static final ResourceExtension EXT = ResourceExtension.builder()
            .addResource(new ConsultationFormResource())
            .build();

    @Test
    void can_create_and_retrieve_consultation_form() {
        Response response = EXT.target("/consultations")
                .request()
                .post(Entity.json(Entity.json(ConsultationForm.class)));

        assertThat(response.getStatus(), is(201));
        assertThat(response.getHeaderString("Location"), is("http://localhost:0/consultations"));

        ConsultationForm createdEntity = response.readEntity(ConsultationForm.class);
        assertThat(createdEntity.getProductName(), is("genovian-pear"));
    }
}

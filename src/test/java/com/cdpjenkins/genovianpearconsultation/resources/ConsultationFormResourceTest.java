package com.cdpjenkins.genovianpearconsultation.resources;

import com.cdpjenkins.genovianpearconsultation.api.Answer;
import com.cdpjenkins.genovianpearconsultation.api.ConsultationForm;
import com.cdpjenkins.genovianpearconsultation.api.Question;
import com.cdpjenkins.genovianpearconsultation.core.ConsultationFormService;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static com.cdpjenkins.genovianpearconsultation.api.ConsultationForm.Status.COMPLETED;
import static com.cdpjenkins.genovianpearconsultation.api.ConsultationForm.Status.IN_PROGRESS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

@ExtendWith(DropwizardExtensionsSupport.class)
class ConsultationFormResourceTest {

    private static final ConsultationFormService consultationFormService = new ConsultationFormService();

    private static final ResourceExtension EXT = ResourceExtension.builder()
            .addResource(new ConsultationFormResource(consultationFormService))
            .build();

    @BeforeEach
    void setUp() {
        consultationFormService.reset();
    }

    @Test
    void can_create_and_retrieve_consultation_form() {
        Response response = EXT.target("/consultations")
                .request()
                .post(Entity.json(new ConsultationForm("genovian-pear")));

        assertThat(response.getStatus(), is(201));
        assertThat(response.getHeaderString("Location"), is("http://localhost:0/consultations/1"));

        ConsultationForm createdConsultationForm = response.readEntity(ConsultationForm.class);
        assertThat(createdConsultationForm.getProductName(), is("genovian-pear"));
        assertThat(createdConsultationForm.getId(), is(1));
        assertThat(createdConsultationForm.getStatus(), is(IN_PROGRESS));

        Response getResponse = EXT.target("/consultations/1")
                .request()
                .get();
        assertThat(getResponse.getStatus(), is(200));
        assertThat(getResponse.readEntity(ConsultationForm.class), is(createdConsultationForm));
    }

    @Test
    void consultation_form_has_questions_once_persisted() {
        EXT.target("/consultations")
                .request()
                .post(Entity.json(new ConsultationForm("genovian-pear")));

        ConsultationForm consultationForm = getConsultationForm();

        assertThat(consultationForm.getQuestions(),
                contains(
                        new Question(1, "Please enter your height."),
                        new Question(2, "Please enter your weight."),
                        new Question(3, "Please enter your blood pressure."),
                        new Question(4, "Are you allergic to any of the ingredients in this medication?")
                ));
    }

    @Test
    void can_post_answer_to_a_question_on_a_consultation_form() {
        EXT.target("/consultations")
                .request()
                .post(Entity.json(new ConsultationForm("genovian-pear")));

        Response response = postAnswer(1, 1, "185 cm");
        assertThat(response.getStatus(), is(200));

        ConsultationForm consultationForm = getConsultationForm();

        assertThat(consultationForm.getAnswers(),
                is(List.of(new Answer(1, "185 cm"))));
    }

    @Test
    void status_is_set_to_completed_when_all_questions_are_answered() {
        EXT.target("/consultations")
                .request()
                .post(Entity.json(new ConsultationForm("genovian-pear")));

        postAnswer(1, 1, "185 cm");
        postAnswer(1, 2, "80 kg");
        postAnswer(1, 3, "120/80");
        postAnswer(1, 4, "No");

        assertThat(getConsultationForm().getStatus(), is(COMPLETED));
    }

    private static ConsultationForm getConsultationForm() {
        return EXT.target("/consultations/1")
                .request()
                .get()
                .readEntity(ConsultationForm.class);
    }

    private static Response postAnswer(int consultationFormId, int questionId, String answerText) {
        Response response = EXT.target(String.format("/consultations/%d/questions/%d/answer", consultationFormId, questionId))
                .request()
                .post(Entity.json(new Answer(questionId, answerText)));
        assertThat(response.getStatus(), is(200));

        return response;
    }
}

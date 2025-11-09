package com.cdpjenkins.genovianpearconsultation.core;

import com.cdpjenkins.genovianpearconsultation.api.Answer;
import com.cdpjenkins.genovianpearconsultation.api.ConsultationForm;
import com.cdpjenkins.genovianpearconsultation.api.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsultationFormService {
    AtomicInteger idAllocator = new AtomicInteger();

    // In real life, this would be a database
    Map<Integer, ConsultationForm> consultationFormRepository = new HashMap<>();

    public ConsultationForm createConsultationForm(ConsultationForm consultationForm) {
        consultationForm.setId(idAllocator.incrementAndGet());

        consultationForm.setQuestions(
                List.of(
                        new Question(1, "Please enter your height."),
                        new Question(2, "Please enter your weight."),
                        new Question(3, "Please enter your blood pressure."),
                        new Question(4, "Are you allergic to any of the ingredients in this medication?")
                )
        );

        consultationForm.setAnswers(new ArrayList<>());

        consultationFormRepository.put(consultationForm.getId(), consultationForm);

        return consultationForm;
    }

    public ConsultationForm getConsultationForm(int consultationId) {
        return consultationFormRepository.get(consultationId);
    }

    public void answerQuestion(int consultationId, int questionId, Answer answer) {
        ConsultationForm consultationForm = consultationFormRepository.get(consultationId);

        if (consultationForm.getAnswer(questionId) == null) {
            List<Answer> answers = consultationForm.getAnswers();
            answers.add(answer);
        } else {
            // TODO throw an exception that we define, and map it to an appropriate HTTP status code
            throw new IllegalArgumentException("Question already answered");
        }

        if (consultationForm.allQuestionsAreAnswered()) {
            consultationForm.setStatus(ConsultationForm.Status.COMPLETED);
        }
    }

    public void submitConsultation(int consultationId) throws InvalidConsultationFormStateException {
        ConsultationForm consultationForm = consultationFormRepository.get(consultationId);

        if (consultationForm.getStatus() != ConsultationForm.Status.COMPLETED) {
            throw new InvalidConsultationFormStateException("The form can only be submitted when it is completed.");
        }

        switch (decideApproval(consultationForm)) {
            case REJECTED:
                consultationForm.setStatus(ConsultationForm.Status.REJECTED);
                consultationForm.setRejectionReason("User is allergic to the ingredients");
                return;
            case APPROVED:
                consultationForm.setStatus(ConsultationForm.Status.APPROVED);
                return;
            default:
                throw new IllegalStateException("Unexpected value: " + consultationForm.getStatus());
        }
    }

    private ConsultationForm.Status decideApproval(ConsultationForm consultationForm) {
        if (consultationForm.getAnswer(4).getAnswer().equals("Yes")) {
            return ConsultationForm.Status.REJECTED;
        } else {
            return ConsultationForm.Status.APPROVED;
        }
    }

    /**
     * Only for use in tests!
     *
     * Reset this service's state in between tests.
     */
    public void reset() {
        idAllocator.set(0);
    }
}

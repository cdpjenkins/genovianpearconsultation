package com.cdpjenkins.genovianpearconsultation.core;

import com.cdpjenkins.genovianpearconsultation.api.ConsultationForm;
import com.cdpjenkins.genovianpearconsultation.api.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsultationFormService {
    AtomicInteger idAllocator = new AtomicInteger();

    // In real life, this would be a database
    Map<Integer, ConsultationForm> consultationFormStorage = new HashMap<>();

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

        consultationFormStorage.put(consultationForm.getId(), consultationForm);

        return consultationForm;
    }

    public ConsultationForm getConsultationForm(int consultationId) {
        return consultationFormStorage.get(consultationId);
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

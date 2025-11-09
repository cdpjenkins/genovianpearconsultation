package com.cdpjenkins.genovianpearconsultation.core;

import com.cdpjenkins.genovianpearconsultation.api.ConsultationForm;

import java.util.concurrent.atomic.AtomicInteger;

public class ConsultationFormService {
    AtomicInteger idAllocator = new AtomicInteger();

    public ConsultationForm createConsultationForm(ConsultationForm consultationForm) {
        consultationForm.setId(idAllocator.incrementAndGet());

        return consultationForm;
    }
}

package com.cdpjenkins.genovianpearconsultation.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Jacksonized
@AllArgsConstructor
public class ConsultationForm {
    Integer id;
    String productName;
    List<Question> questions;

    public ConsultationForm(String productName) {
        this(null, productName, new ArrayList<>());
    }

    public List<Question> getQuestions() {
        return questions;
    }
}

package com.cdpjenkins.genovianpearconsultation.api;

import jdk.jshell.Snippet;
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
    List<Answer> answers;

    public ConsultationForm(String productName) {
        this(null, productName, new ArrayList<>(), new ArrayList<>());
    }

    public Answer getAnswer(int questionId) {
        return answers.stream()
                .filter(answer -> answer.getQuestionId() == questionId)
                .findFirst()
                .orElse(null);
    }

    public Status getStatus() {
        return Status.IN_PROGRESS;
    }

    public enum Status {
        IN_PROGRESS,
        COMPLETED,
        APPROVED,
        REJECTED
    }
}

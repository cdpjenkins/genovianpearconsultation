package com.cdpjenkins.genovianpearconsultation.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@AllArgsConstructor
@Jacksonized

public class Answer {
    int questionId;
    String answer;
}

package com.cdpjenkins.genovianpearconsultation.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@AllArgsConstructor
@Jacksonized
public class Question {
    int id;
    String question;
}

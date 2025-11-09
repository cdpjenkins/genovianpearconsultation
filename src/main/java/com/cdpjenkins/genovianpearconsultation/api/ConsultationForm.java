package com.cdpjenkins.genovianpearconsultation.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@AllArgsConstructor
public class ConsultationForm {
    Integer id;
    String productName;

    public ConsultationForm(String productName) {
        this(null, productName);
    }
}

package com.cdpjenkins.genovianpearconsultation.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@AllArgsConstructor
public class ConsultationForm {
    String productName;
}

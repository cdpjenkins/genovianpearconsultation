package com.cdpjenkins.genovianpearconsultation.exceptions;

public class InvalidConsultationFormStateException extends ConsultationFormException {
    public InvalidConsultationFormStateException(String errorMessage) {
        super(409, errorMessage);
    }
}

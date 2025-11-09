package com.cdpjenkins.genovianpearconsultation.exceptions;

public class InvalidConsultationFormException extends ConsultationFormException {
    public InvalidConsultationFormException(String errorMessage) {
        super(400, errorMessage);
    }
}

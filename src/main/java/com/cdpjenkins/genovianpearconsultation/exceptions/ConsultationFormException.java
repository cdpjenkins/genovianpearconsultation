package com.cdpjenkins.genovianpearconsultation.exceptions;

public abstract class ConsultationFormException extends Exception{
    private int httpStatus;
    private String errorMessage;

    public int getHttpStatus() { return httpStatus; }
    public String getErrorMessage() { return errorMessage; }

    public ConsultationFormException(int httpStatus, String errorMessage) {
        super(errorMessage);

        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }
}

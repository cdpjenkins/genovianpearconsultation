package com.cdpjenkins.genovianpearconsultation.resources;

import com.cdpjenkins.genovianpearconsultation.api.ErrorResponse;
import com.cdpjenkins.genovianpearconsultation.exceptions.InvalidConsultationFormStateException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ConsultationFormExceptionMapper implements ExceptionMapper<InvalidConsultationFormStateException> {
    @Override
    public Response toResponse(final InvalidConsultationFormStateException exception) {
        return Response.status(exception.getHttpStatus())
                .entity(new ErrorResponse(exception.getErrorMessage()))
                .build();
    }
}

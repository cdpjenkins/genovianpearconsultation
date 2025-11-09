package com.cdpjenkins.genovianpearconsultation.resources;

import com.cdpjenkins.genovianpearconsultation.core.InvalidConsultationFormStateException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class InvalidConsultationFormStateExceptionMapper implements ExceptionMapper<InvalidConsultationFormStateException> {
    @Override
    public Response toResponse(final InvalidConsultationFormStateException exception) {
        return Response.status(409)
                .type(MediaType.TEXT_PLAIN_TYPE)
                .entity(exception.getMessage())
                .build();
    }
}

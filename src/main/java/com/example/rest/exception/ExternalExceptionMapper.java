package com.example.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jure.repe
 */
@Slf4j
@Provider
public class ExternalExceptionMapper implements ExceptionMapper<ExternalException> {

    @Override
    public Response toResponse(ExternalException exception) {
        log.error("Request failed with an exception:", exception);
        return Response.status(500).build();
    }
}

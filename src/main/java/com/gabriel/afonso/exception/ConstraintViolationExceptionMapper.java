package com.gabriel.afonso.exception;

import io.netty.handler.codec.http.HttpResponseStatus;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException>{


    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(ConstraintViolationException e) {

        var prolem = Problem.builder()
                .message("Um ou mais campos estão inválidos corrija e tente novamente")
                .status_code((long) HttpResponseStatus.BAD_REQUEST.code())
                .build();

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(prolem)
                .build();
    }
}

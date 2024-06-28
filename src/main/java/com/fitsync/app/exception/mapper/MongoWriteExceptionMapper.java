package com.fitsync.app.exception.mapper;

import com.fitsync.app.exception.ErrorResponse;
import com.mongodb.MongoWriteException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.resteasy.reactive.RestResponse;

@Provider
public class MongoWriteExceptionMapper implements ExceptionMapper<MongoWriteException> {

    @Override
    public Response toResponse(MongoWriteException exception) {
        return Response.status(RestResponse.Status.CONFLICT)
                .entity(ErrorResponse.builder()
                        .error("Database Error")
                        .message("A conflict occurred while writing to the database.")
                        .details(exception.getMessage())
                        .build())
                .build();
    }
}

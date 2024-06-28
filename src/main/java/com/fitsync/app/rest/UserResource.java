package com.fitsync.app.rest;

import com.fitsync.app.persistence.entity.User;
import com.fitsync.app.service.UserService;
import io.quarkus.resteasy.reactive.links.InjectRestLinks;
import io.quarkus.resteasy.reactive.links.RestLink;
import io.quarkus.resteasy.reactive.links.RestLinkType;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.common.util.RestMediaType;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON, RestMediaType.APPLICATION_HAL_JSON })
public class UserResource {
    private final UserService service;

    @Inject
    public UserResource(UserService service) {
        this.service = service;
    }


    @GET
    @Path("/{id}")
    @RestLink(rel = "self", entityType = User.class)
    @InjectRestLinks(RestLinkType.INSTANCE)
    public Uni<Response> find(@PathParam("id") String id) {
        return service.findById(id)
                .onItem().ifNotNull().transform(u -> Response.ok(u).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/name/{username}")
    @RestLink(rel = "self.username", entityType = User.class)
    @InjectRestLinks(RestLinkType.INSTANCE)
    public Uni<Response> findByName(@PathParam("username") String username) {
        return service.findByName(username)
                .onItem().ifNotNull().transform(u -> Response.ok(u).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Path("/{username}")
    @InjectRestLinks(RestLinkType.INSTANCE)
    public Uni<Response> create(@PathParam("username") String username) {
        return service.create(username)
                .map(u -> Response.ok(u).build());
    }

    @DELETE
    @Path("/name/{username}")
    @RestLink(rel = "delete", entityType = User.class)
    public Response delete(@PathParam("username") String username) {
        service.deleteByName(username);
        return Response.noContent().build();
    }


}

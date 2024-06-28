package com.fitsync.app.persistence.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.resteasy.reactive.links.RestLinkId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@MongoEntity(collection="Users")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class User {

    // TODO - Handle with keycloak

    @RestLinkId
    public String id;
    public String username;
}

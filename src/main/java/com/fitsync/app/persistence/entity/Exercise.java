package com.fitsync.app.persistence.entity;


import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.resteasy.reactive.links.RestLinkId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@MongoEntity(collection="Exercises")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Exercise {

    @RestLinkId
    public String id;
    public String name;
    public String description;
}

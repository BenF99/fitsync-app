package com.fitsync.app.persistence.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.resteasy.reactive.links.RestLinkId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@MongoEntity(collection="Workouts")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Workout {

    @RestLinkId
    public String id;
    public String ownerId;
    public LocalDateTime date;
    public String notes;
    public List<Exercise> exercises;

}

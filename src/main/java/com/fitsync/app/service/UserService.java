package com.fitsync.app.service;

import com.fitsync.app.persistence.entity.User;
import com.fitsync.app.persistence.repository.UserRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class UserService {

    @Inject
    private final UserRepository r;

    public UserService(UserRepository r) {
        this.r = r;
    }

    public Uni<User> findById(String id){
        return r.find("_id", id).firstResult();
    }

    public Uni<User> findByName(String username){
        return r.find("username", username).firstResult();
    }

    public Uni<User> create(String username){
        return r.persist(User.builder()
                .username(username)
                .id(UUID.randomUUID().toString())
                .build());
    }


    public void deleteByName(String username) {
        r.delete("username = ?1", username);
    }
}

package com.example.documents.repository;

import com.example.documents.model.Identity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IdentityRepository extends CrudRepository<Identity, UUID> {

    Identity findByUsernameAndPassword(String username, String password);
}

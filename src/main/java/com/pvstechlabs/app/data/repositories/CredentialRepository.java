package com.pvstechlabs.app.data.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.pvstechlabs.app.data.entities.Credential;
import com.pvstechlabs.app.data.entities.User;

public interface CredentialRepository extends CrudRepository<Credential, Long> {
	Boolean existsByUsername(String username);

	Optional<Credential> findByUsername(String username);
}

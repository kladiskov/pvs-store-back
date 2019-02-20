package com.pvstechlabs.app.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pvstechlabs.app.data.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	User findByUserId(Long userId);

}

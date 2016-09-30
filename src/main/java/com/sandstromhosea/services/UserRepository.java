package com.sandstromhosea.services;

import com.sandstromhosea.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hoseasandstrom on 9/30/16.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}

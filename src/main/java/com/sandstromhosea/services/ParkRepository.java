package com.sandstromhosea.services;

import com.sandstromhosea.entities.Park;
import com.sandstromhosea.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hoseasandstrom on 10/3/16.
 */
public interface ParkRepository extends CrudRepository<Park, Integer> {
    Iterable<Park> findByUsers(User user);
    Park findFirstByEventid (int id);
}

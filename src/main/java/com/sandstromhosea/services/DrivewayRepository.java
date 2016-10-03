package com.sandstromhosea.services;

import com.sandstromhosea.entities.Driveway;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hoseasandstrom on 9/30/16.
 */
public interface DrivewayRepository extends CrudRepository<Driveway, Integer> {
    Driveway findFirstByName(String name);
    Driveway findFirstById (int id);
}

package com.nc.finalproject.service;

import com.nc.finalproject.model.Pet;

import java.util.List;

/**
 * Interface for operations with entity {@link Pet} and associated table in the database.
 *
 * @author WildDed
 * @version 1.0
 */

public interface PetService {
    Pet findByName(String name);

    List<Pet> findAll();

    <S extends Pet> S saveAndFlush(S s);

    void deleteById(Long aLong);

    List<Pet> findAllByUsername(String username);
}

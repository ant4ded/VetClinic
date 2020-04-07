package com.nc.finalproject.service;

import com.nc.finalproject.model.VetService;

import java.util.List;
import java.util.Optional;

/**
 * Interface for operations with entity {@link VetService} and associated table in the database.
 *
 * @author WildDed
 * @version 1.0
 */

public interface VetServiceService {
    Optional<VetService> findById(Long id);

    VetService findByName(String name);

    List<VetService> findAll();

    void deleteById(Long id);

    <S extends VetService> S saveAndFlush(S s);
}

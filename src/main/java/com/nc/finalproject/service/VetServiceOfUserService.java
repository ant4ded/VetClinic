package com.nc.finalproject.service;

import com.nc.finalproject.model.VetServiceOfUser;

import java.util.List;
import java.util.Optional;

/**
 * Interface for operations with entity {@link VetServiceOfUser} and associated table in the database.
 *
 * @author WildDed
 * @version 1.0
 */

public interface VetServiceOfUserService {
    List<VetServiceOfUser> findAllById(Long id);

    List<VetServiceOfUser> findAllByNameDoctorAndDate(String nameDoctor, String date);

    Optional<VetServiceOfUser> findById(Long aLong);

    void deleteById(Long aLong);

    <S extends VetServiceOfUser> S saveAndFlush(S s);
}

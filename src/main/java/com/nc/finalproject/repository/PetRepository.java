package com.nc.finalproject.repository;

import com.nc.finalproject.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for {@link Pet}
 * extends {@link JpaRepository}.
 *
 * @author WildDed
 * @version 1.0
 */

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Pet findByName(String name);

    Pet findByPetType(String petType);

    @Override
    List<Pet> findAll();

    @Override
    <S extends Pet> S saveAndFlush(S s);

    @Override
    void deleteById(Long aLong);

    @Query(value = "select * from pets where username = :username", nativeQuery = true)
    List<Pet> findAllByUsername(@Param("username") String username);
}

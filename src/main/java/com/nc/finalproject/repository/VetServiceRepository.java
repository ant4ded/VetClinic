package com.nc.finalproject.repository;

import com.nc.finalproject.model.VetService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for {@link VetService}
 * extends {@link JpaRepository}.
 *
 * @author WildDed
 * @version 1.0
 */

@Repository
public interface VetServiceRepository extends JpaRepository<VetService, Long> {
    @Override
    Optional<VetService> findById(Long id);

    @Query(value = "select * from vet_services where name = :currentName", nativeQuery = true)
    VetService findByName(@Param("currentName") String name);

    @Override
    List<VetService> findAll();

    @Override
    void deleteById(Long id);

    @Override
    <S extends VetService> S saveAndFlush(S s);
}

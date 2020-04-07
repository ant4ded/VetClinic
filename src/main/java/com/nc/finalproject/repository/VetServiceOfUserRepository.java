package com.nc.finalproject.repository;

import com.nc.finalproject.model.VetServiceOfUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for {@link VetServiceOfUser}
 * extends {@link JpaRepository}.
 *
 * @author WildDed
 * @version 1.0
 */

@Repository
public interface VetServiceOfUserRepository extends JpaRepository<VetServiceOfUser, Long> {
    @Query(value = "select * from user_services where id_user = :id", nativeQuery = true)
    List<VetServiceOfUser> findAllById(@Param("id") Long id);

    @Query(value = "select * from user_services where name_doctor = :nameDoctor and date = :dateService", nativeQuery = true)
    List<VetServiceOfUser> findAllByNameDoctorAndDate(@Param("nameDoctor") String nameDoctor, @Param("dateService") String date);

    @Override
    Optional<VetServiceOfUser> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    <S extends VetServiceOfUser> S saveAndFlush(S s);
}

package com.nc.finalproject.repository;

import com.nc.finalproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for {@link User}
 * extends {@link JpaRepository}.
 *
 * @author WildDed
 * @version 1.0
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    void deleteByUsername(String username);
    User findByUsername(String username);

    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long aLong);

    @Query(value = "select roles.name from final_project.roles " +
            "inner join user_roles on roles.id = user_roles.role_id " +
            "inner join users on user_roles.user_id = users.id where users.username = :#{#username}", nativeQuery = true)
    String findRoleByUsername(@Param("username") String username);

    @Query(value = "select * from final_project.users " +
            "inner join user_roles on users.id = user_roles.user_id " +
            "inner join roles on user_roles.role_id = roles.id " +
            "where name = \"ROLE_DOCTOR\"", nativeQuery = true)
    List<User> findDoctors();

    @Override
    void deleteById(Long aLong);

    @Override
    <S extends User> S saveAndFlush(S s);
}

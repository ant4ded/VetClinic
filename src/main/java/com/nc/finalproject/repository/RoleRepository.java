package com.nc.finalproject.repository;

import com.nc.finalproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link Role}
 * extends {@link JpaRepository}.
 *
 * @author WildDed
 * @version 1.0
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}

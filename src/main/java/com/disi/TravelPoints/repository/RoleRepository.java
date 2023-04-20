package com.disi.TravelPoints.repository;

import com.disi.TravelPoints.enums.RoleName;
import com.disi.TravelPoints.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName name);
}

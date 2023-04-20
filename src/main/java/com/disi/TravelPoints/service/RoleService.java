package com.disi.TravelPoints.service;

import com.disi.TravelPoints.enums.RoleName;
import com.disi.TravelPoints.model.Role;
import com.disi.TravelPoints.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findByRoleName(RoleName name) {
        return roleRepository.findByName(name);
    }
}

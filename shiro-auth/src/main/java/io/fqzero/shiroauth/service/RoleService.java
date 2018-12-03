package io.fqzero.shiroauth.service;

import io.fqzero.shiroauth.entity.Role;
import io.fqzero.shiroauth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getRole(String role){
       return roleRepository.findByRole(role);
    }
}

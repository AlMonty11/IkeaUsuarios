package com.example.montaneralbertomyikea.repositories;

import com.example.montaneralbertomyikea.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByNombreRol(String name);

}

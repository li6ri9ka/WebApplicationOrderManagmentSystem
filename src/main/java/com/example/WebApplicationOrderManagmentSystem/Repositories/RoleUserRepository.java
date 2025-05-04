package com.example.WebApplicationOrderManagmentSystem.Repositories;

import com.example.WebApplicationOrderManagmentSystem.Model.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleUserRepository extends JpaRepository<RoleUser, Long> {
    Optional<RoleUser> findById(Long id);

}

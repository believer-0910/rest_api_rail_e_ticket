package com.example.rail_e_ticket_api.repository;

import com.example.rail_e_ticket_api.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OwnerRepository extends JpaRepository<Owner, UUID> {
    Optional<Owner> findByUsername(String username);

    boolean existsByUsernameOrEmail(String username, String email);
}

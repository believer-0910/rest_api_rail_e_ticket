package com.example.rail_e_ticket_api.repository;

import com.example.rail_e_ticket_api.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner,Long> {
    Optional<Owner> findByUsername(String username);
}

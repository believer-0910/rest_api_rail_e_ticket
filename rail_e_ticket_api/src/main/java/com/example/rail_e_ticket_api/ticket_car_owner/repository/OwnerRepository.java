package com.example.rail_e_ticket_api.ticket_car_owner.repository;

import com.example.rail_e_ticket_api.ticket_car_owner.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner,Long> {
    Optional<Owner> findByUserName(String username);
}

package com.example.rail_e_ticket_api.repository;

import com.example.rail_e_ticket_api.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    Optional<Destination> findByCode(String code);
}

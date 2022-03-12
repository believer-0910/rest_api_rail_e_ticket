package com.example.rail_e_ticket_api.repository;

import com.example.rail_e_ticket_api.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StationRepository extends JpaRepository<Station, Long> {
    Optional<Station> findByDestinationIdAndName(Long destination_id, String name);
}

package com.example.rail_e_ticket_api.repository;

import com.example.rail_e_ticket_api.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Long> {
    Optional<Station> findByDestinationIdAndName(Long destinationId, String name);
    List<Station> findStationsByDestinationId(Long destinationId);
}

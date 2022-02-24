package com.example.rail_e_ticket_api.repository;

import com.example.rail_e_ticket_api.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PriceRepository extends JpaRepository<Price, UUID> {
    Optional<Price> findByFromStationIdAndToStationId(UUID fromStation_id, UUID toStation_id);
}

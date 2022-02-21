package com.example.rail_e_ticket_api.repository;

import com.example.rail_e_ticket_api.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {
    Optional<Price> findByFromStationIdAndToStationId(Long fromStationId, Long toStationId);
}

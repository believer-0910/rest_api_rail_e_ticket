package com.example.rail_e_ticket_api.repository;

import com.example.rail_e_ticket_api.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TrainRepository extends JpaRepository<Train, Long> {
    Optional<Train> findByCode(String code);
}

package com.example.rail_e_ticket_api.repository;

import com.example.rail_e_ticket_api.entity.TrainDestination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainDestinationRepository extends JpaRepository<TrainDestination, Long> {
}

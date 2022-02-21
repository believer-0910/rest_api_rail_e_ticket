package com.example.rail_e_ticket_api.repository;

import com.example.rail_e_ticket_api.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Long> {
}

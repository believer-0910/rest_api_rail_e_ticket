package com.example.rail_e_ticket_api.repository;

import com.example.rail_e_ticket_api.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
}
package com.example.rail_e_ticket_api.repository;

import com.example.rail_e_ticket_api.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}

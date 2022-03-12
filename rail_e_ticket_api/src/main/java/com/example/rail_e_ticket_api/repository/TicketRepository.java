package com.example.rail_e_ticket_api.repository;

import com.example.rail_e_ticket_api.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByNumSeat(byte numSeat);
}

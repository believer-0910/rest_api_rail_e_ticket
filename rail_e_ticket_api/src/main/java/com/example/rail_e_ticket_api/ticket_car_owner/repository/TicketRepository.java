package com.example.rail_e_ticket_api.ticket_car_owner.repository;

import com.example.rail_e_ticket_api.ticket_car_owner.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    Optional<Ticket> findByNumSeat(byte numSeat);
}

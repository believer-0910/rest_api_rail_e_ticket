package com.example.rail_e_ticket_api.controller;

import com.example.rail_e_ticket_api.payload.TicketDto;
import com.example.rail_e_ticket_api.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ticket")
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/add")
    public ResponseEntity<?> addTicket(@RequestBody @Valid TicketDto ticketDto) {
        return ResponseEntity.ok(ticketService.add(ticketDto));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getTicketById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(ticketService.getById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getTicketList() {
        return ResponseEntity.ok(ticketService.getList());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateTicket(@PathVariable("id") Long id, @RequestBody TicketDto ticketDto) {
        return ResponseEntity.ok(ticketService.updateById(id, ticketDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ticketService.deleteById(id));
    }
}

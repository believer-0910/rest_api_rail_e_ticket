package com.example.rail_e_ticket_api.controller;

import com.example.rail_e_ticket_api.payload.DestinationDto;
import com.example.rail_e_ticket_api.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/destination")
public class DestinationController {

    private final DestinationService destinationService;

    @PostMapping("/add")
    public ResponseEntity<?> addDestination(@RequestBody @Valid DestinationDto destinationDto) {
        return ResponseEntity.ok(destinationService.add(destinationDto));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getDestinationById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(destinationService.getById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getDestinationList() {
        return ResponseEntity.ok(destinationService.getList());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateDestination(@PathVariable("id") Long id, @RequestBody DestinationDto destinationDto) {
        return ResponseEntity.ok(destinationService.updateById(id, destinationDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteDestination(@PathVariable("id") Long id) {
        return ResponseEntity.ok(destinationService.deleteById(id));
    }
}

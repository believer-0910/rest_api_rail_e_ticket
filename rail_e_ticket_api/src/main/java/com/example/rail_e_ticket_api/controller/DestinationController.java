package com.example.rail_e_ticket_api.controller;

import com.example.rail_e_ticket_api.payload.DestinationDto;
import com.example.rail_e_ticket_api.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/destination")
public class DestinationController {

    private final DestinationService destinationService;

    @PostMapping
    public ResponseEntity<?> addDestination(@Valid @RequestBody DestinationDto destinationDto) {
        return ResponseEntity.ok(destinationService.add(destinationDto));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getDestinationById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(destinationService.getById(id));
    }

    @GetMapping
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

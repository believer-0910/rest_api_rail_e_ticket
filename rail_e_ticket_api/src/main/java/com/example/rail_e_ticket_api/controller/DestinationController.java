package com.example.rail_e_ticket_api.controller;

import com.example.rail_e_ticket_api.dto.DestinationDto;
import com.example.rail_e_ticket_api.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/destination")
public class DestinationController {

    private final DestinationService destinationService;

    @PostMapping("/add")
    public ResponseEntity<?> addDestination(
            @RequestBody @Valid DestinationDto destinationDto
    ) {
        return ResponseEntity.ok(destinationService.add(destinationDto));
    }
}

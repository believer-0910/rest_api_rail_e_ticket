package com.example.rail_e_ticket_api.controller;

import com.example.rail_e_ticket_api.payload.TrainDestinationDto;
import com.example.rail_e_ticket_api.service.TrainDestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/train_destination")
public class TrainDestinationController {
    private final TrainDestinationService trainDestinationService;

    @PostMapping("/add")
    public ResponseEntity<?> addTrainDestination(
            @RequestBody @Valid TrainDestinationDto trainDestinationDto
    ) {
        return ResponseEntity.ok(trainDestinationService.add(trainDestinationDto));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getTrainDestinationById(
            @RequestParam("id") Long id
    ) {
        return ResponseEntity.ok(trainDestinationService.getById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getTrainDestinationList(){
        return ResponseEntity.ok(trainDestinationService.getList());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateTrainDestination(
            @PathVariable("id") Long id,
            @RequestBody TrainDestinationDto trainDestinationDto
    ) {
        return ResponseEntity.ok(trainDestinationService.updateById(id, trainDestinationDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTrainDestination(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(trainDestinationService.deleteById(id));
    }
}

package com.example.rail_e_ticket_api.controller;

import com.example.rail_e_ticket_api.payload.StationDto;
import com.example.rail_e_ticket_api.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/station")
public class StationController {
    private final StationService stationService;

    @PostMapping("/add")
    public ResponseEntity<?> addStation(
            @RequestBody @Valid StationDto stationDto
    ) {
        return ResponseEntity.ok(stationService.add(stationDto));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getStationById(
            @RequestParam("id") UUID id
    ) {
        return ResponseEntity.ok(stationService.getById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getStationList(){
        return ResponseEntity.ok(stationService.getList());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateStation(
            @PathVariable("id") UUID id,
            @RequestBody StationDto stationDto
    ) {
        return ResponseEntity.ok(stationService.updateById(id, stationDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStation(
            @PathVariable("id") UUID id
    ) {
        return ResponseEntity.ok(stationService.deleteById(id));
    }
}

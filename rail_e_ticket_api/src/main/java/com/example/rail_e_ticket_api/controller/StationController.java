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

    @PostMapping
    public ResponseEntity<?> addStation(
            @RequestBody @Valid StationDto stationDto
    ) {
        return ResponseEntity.ok(stationService.add(stationDto));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getStationById(
            @RequestParam("id") Long id
    ) {
        return ResponseEntity.ok(stationService.getById(id));
    }

    @GetMapping
    public ResponseEntity<?> getStationList(){
        return ResponseEntity.ok(stationService.getList());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateStation(
            @PathVariable("id") Long id,
            @RequestBody StationDto stationDto
    ) {
        return ResponseEntity.ok(stationService.updateById(id, stationDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStation(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(stationService.deleteById(id));
    }


}

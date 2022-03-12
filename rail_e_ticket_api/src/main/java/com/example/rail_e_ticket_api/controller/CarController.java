package com.example.rail_e_ticket_api.controller;

import com.example.rail_e_ticket_api.payload.CarDto;
import com.example.rail_e_ticket_api.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car")
public class CarController {
    private final CarService carService;

    @PostMapping
    public ResponseEntity<?> addCar(@Valid @RequestBody CarDto carDto) {
        return ResponseEntity.ok(carService.add(carDto));
    }

    @GetMapping("/search") public ResponseEntity<?> getCarById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(carService.getById(id));
    }

    @GetMapping
    public ResponseEntity<?> getCarList() {
        return ResponseEntity.ok(carService.getList());
    }

    @PutMapping("{id}") public ResponseEntity<?> updateCar(@PathVariable("id") Long id, @RequestBody CarDto carDto) {
        return ResponseEntity.ok(carService.updateById(id, carDto));
    }

    @DeleteMapping("{id}") public ResponseEntity<?> deleteCar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(carService.deleteById(id));
    }

}

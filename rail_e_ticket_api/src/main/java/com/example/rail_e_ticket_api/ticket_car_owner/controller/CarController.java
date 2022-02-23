package com.example.rail_e_ticket_api.ticket_car_owner.controller;

import com.example.rail_e_ticket_api.dto.DestinationDto;
import com.example.rail_e_ticket_api.service.DestinationService;
import com.example.rail_e_ticket_api.ticket_car_owner.dto.CarDto;
import com.example.rail_e_ticket_api.ticket_car_owner.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car")
public class CarController {
    private final CarService carService;

    @PostMapping("/add")
    public ResponseEntity<?> addCar(@RequestBody @Valid CarDto carDto) {
        return ResponseEntity.ok(carService.add(carDto));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getCarById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(carService.getById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getCarList() {
        return ResponseEntity.ok(carService.getList());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCar(@PathVariable("id") Long id, @RequestBody CarDto carDto) {
        return ResponseEntity.ok(carService.updateById(id, carDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(carService.deleteById(id));
    }

}

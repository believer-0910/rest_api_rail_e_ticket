package com.example.rail_e_ticket_api.controller;

import com.example.rail_e_ticket_api.payload.PriceDto;
import com.example.rail_e_ticket_api.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/price")
public class PriceController {
    private final PriceService priceService;

    @PostMapping("/add")
    public ResponseEntity<?> addPrice(
            @RequestBody @Valid PriceDto priceDto
    ) {
        return ResponseEntity.ok(priceService.add(priceDto));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getPriceById(
            @RequestParam("id") Long id
    ) {
        return ResponseEntity.ok(priceService.getById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getPriceList(){
        return ResponseEntity.ok(priceService.getList());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updatePrice(
            @PathVariable("id") Long id,
            @RequestBody PriceDto priceDto
    ) {
        return ResponseEntity.ok(priceService.updateById(id, priceDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePrice(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(priceService.deleteById(id));
    }
}

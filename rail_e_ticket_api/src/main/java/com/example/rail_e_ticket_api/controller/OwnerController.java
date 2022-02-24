package com.example.rail_e_ticket_api.controller;

import com.example.rail_e_ticket_api.payload.OwnerDto;
import com.example.rail_e_ticket_api.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/owner")
public class OwnerController {
    private final OwnerService ownerService;

    @PostMapping("/add")
    public ResponseEntity<?> addOwner(@RequestBody @Valid OwnerDto ownerDto) {
        return ResponseEntity.ok(ownerService.add(ownerDto));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getOwnerById(@RequestParam("id") UUID id) {
        return ResponseEntity.ok(ownerService.getById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getOwnerList() {
        return ResponseEntity.ok(ownerService.getList());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateOwner(@PathVariable("id") UUID id, @RequestBody OwnerDto ownerDto) {
        return ResponseEntity.ok(ownerService.updateById(id, ownerDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(ownerService.deleteById(id));
    }
}

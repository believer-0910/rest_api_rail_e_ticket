package com.example.rail_e_ticket_api.controller;

import com.example.rail_e_ticket_api.payload.OwnerDto;
import com.example.rail_e_ticket_api.repository.OwnerRepository;
import com.example.rail_e_ticket_api.response.ApiResponse;
import com.example.rail_e_ticket_api.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final OwnerService ownerService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addOwner(@RequestBody @Valid OwnerDto ownerDto) {
        return ResponseEntity.ok(ownerService.add(ownerDto));
    }


    @PostMapping("/check")
    public ResponseEntity<?> checkOwnerAndSendVerification(@RequestBody OwnerDto ownerDto) {
        ApiResponse response = ownerService.checkOwnerAndSendVerification(ownerDto);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/verify/{email}")
    public ResponseEntity<?> verifyOwner(@PathVariable String email) {
        ApiResponse response = ownerService.verifyOwner(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getOwnerByUsername(@PathVariable String username) {
        return ResponseEntity.ok(ownerService.getByUsername(username));
    }
}

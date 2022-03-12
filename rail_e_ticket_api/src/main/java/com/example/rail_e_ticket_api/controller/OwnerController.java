package com.example.rail_e_ticket_api.controller;

import com.example.rail_e_ticket_api.payload.OwnerDto;
import com.example.rail_e_ticket_api.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.rail_e_ticket_api.util.interfaces.Url.URL_OWNER;

@RestController
@RequiredArgsConstructor
@RequestMapping(URL_OWNER)
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping("/list")
    public ResponseEntity<?> getOwnerList() {
        return ResponseEntity.ok(ownerService.getList());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateOwner(@PathVariable("id") Long id, @RequestBody OwnerDto ownerDto) {
        return ResponseEntity.ok(ownerService.updateById(id, ownerDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ownerService.deleteById(id));
    }
}

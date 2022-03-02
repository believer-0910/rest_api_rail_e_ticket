package com.example.rail_e_ticket_api.controller;

import com.example.rail_e_ticket_api.payload.TrainDto;
import com.example.rail_e_ticket_api.payload.TrainSearchRequestDTO;
import com.example.rail_e_ticket_api.service.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/train")
public class TrainController {
    private final TrainService trainService;

    @PostMapping("/add")
    public ResponseEntity<?> addTrain(
            @RequestBody @Valid TrainDto trainDto
    ) {
        return ResponseEntity.ok(trainService.add(trainDto));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getTrainById(
            @RequestParam("id") Long id
    ) {
        return ResponseEntity.ok(trainService.getById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getTrainList(){
        return ResponseEntity.ok(trainService.getList());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateTrain(
            @PathVariable("id") Long id,
            @RequestBody TrainDto trainDto
    ) {
        return ResponseEntity.ok(trainService.updateById(id, trainDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTrain(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(trainService.deleteById(id));
    }

//    @GetMapping("/filter")
//    public ResponseEntity<?> getTrains(@RequestBody TrainSearchRequestDTO trainSearchRequestDTO) {
//        trainService.
//    }
}

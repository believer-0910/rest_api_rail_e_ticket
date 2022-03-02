package com.example.rail_e_ticket_api.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrainSearchResponseDTO {
    private long id;
    private String trainCode;
    private String trainType;
    private String fromStation;
    private String fromDestination;
    private LocalDateTime departureTime;
    private String toStation;
    private String toDestination;
    private LocalDateTime arrivalTime;
    private LocalDateTime differenceTime;
    private List<TrainCarResponseDTO> cars;
}

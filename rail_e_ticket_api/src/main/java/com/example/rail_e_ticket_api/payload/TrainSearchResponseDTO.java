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
    private String fromDestination;
    private String toDestination;
    private String fromStation;
    private LocalDateTime departureTime;
    private String toStation;
    private LocalDateTime arrivalTime;
    private long differenceTimeInMinutes;
}

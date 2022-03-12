package com.example.rail_e_ticket_api.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrainSearchRequestDTO {
    private int fromDestinationCode;
    private int toDestinationCode;
    private LocalDate date;
}

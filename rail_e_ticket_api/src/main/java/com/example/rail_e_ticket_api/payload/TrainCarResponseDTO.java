package com.example.rail_e_ticket_api.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrainCarResponseDTO {
    private long id;
    private String type;
    private int availableSeats;
    private double price;
}

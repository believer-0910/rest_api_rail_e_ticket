package com.example.rail_e_ticket_api.payload;

import com.example.rail_e_ticket_api.entity.Train;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    @NotNull
    private Train train;

    @NotNull
    private String type;
    @NotNull
    private String code;
    @NotNull
    private double price;

    @NotNull
    @JsonProperty("num_seats")
    private byte numSeats;
}

package com.example.rail_e_ticket_api.payload;

import com.example.rail_e_ticket_api.entity.Price;
import com.example.rail_e_ticket_api.entity.Train;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainDestinationDto {
    @NotNull
    private Train train;

    @NotNull
    private Price price;

    @NotNull
    @JsonProperty("departure_date")
    private LocalDateTime departureDate;

    @NotNull
    @JsonProperty("arrive_date")
    private LocalDateTime arriveDate;
}

package com.example.rail_e_ticket_api.payload;

import com.example.rail_e_ticket_api.entity.Station;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceDto {

    @NotNull
    @JsonProperty("from_station")
    private Station fromStation;

    @NotNull
    @JsonProperty("to_station")
    private Station toStation;

    @NotNull
    private double amount;

}

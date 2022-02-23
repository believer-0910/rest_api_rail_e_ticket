package com.example.rail_e_ticket_api.payload;

import com.example.rail_e_ticket_api.entity.TrainDestination;
import com.example.rail_e_ticket_api.entity.Car;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.User;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    @NotNull
    @JsonProperty("train_destination")
    private TrainDestination trainDestination;

    @NotNull
    private User user;

    @NotNull
    private Car car;

    @NotNull
    @JsonProperty("num_seat")
    private byte numSeat;
}

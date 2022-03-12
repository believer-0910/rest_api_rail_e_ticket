package com.example.rail_e_ticket_api.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DestinationDto {

    Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer code;
}

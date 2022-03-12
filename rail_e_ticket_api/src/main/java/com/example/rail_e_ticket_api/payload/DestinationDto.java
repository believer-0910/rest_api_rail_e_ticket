package com.example.rail_e_ticket_api.payload;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DestinationDto {

    Long id;

    @NotNull
    private String name;

    @NotNull
    private int code;
}

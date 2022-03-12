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
public class TrainDto {

    private Long id;

    @NotNull
    private String type;

    @NotNull
    private String code;
}

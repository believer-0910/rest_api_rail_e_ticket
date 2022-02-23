package com.example.rail_e_ticket_api.ticket_car_owner.dto;

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
public class OwnerDto {
    @NotNull
    private String name;

    @NotNull
    @JsonProperty("user_name")
    private String userName;

    @NotNull
    private String password;

    @NotNull
    @JsonProperty("user_role")
    private int userRole;
}

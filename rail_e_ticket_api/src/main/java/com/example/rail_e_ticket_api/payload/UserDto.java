package com.example.rail_e_ticket_api.payload;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class UserDto {

    UUID id;

    String name;

    String phoneNumber;


    String username;

    String chatId;

    String state;

    String fromRegion;

    String toRegion;

    LocalDate departureDate;

    String trainNumber;

    String carNumber;



    @CreationTimestamp
    LocalDate createdDate;

    boolean active;


    public UserDto(String name, String phoneNumber, String username, String chatId, String state) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.chatId = chatId;
        this.state = state;
        this.active=true;
    }

    public UserDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserDto setState(String state) {
        this.state = state;
        return this;
    }

    public UserDto setFromRegion(String fromRegion) {
        this.fromRegion = fromRegion;
        return this;
    }

    public UserDto setToRegion(String toRegion) {
        this.toRegion = toRegion;
        return this;
    }

    public UserDto setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
        return this;
    }

    public UserDto setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
        return this;
    }

    public UserDto setCarNumber(String carNumber) {
        this.carNumber = carNumber;
        return this;
    }
}

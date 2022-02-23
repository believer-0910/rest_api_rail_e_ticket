package com.example.rail_e_ticket_api.ticket_car_owner.entity;

import com.example.rail_e_ticket_api.entity.Train;
import com.example.rail_e_ticket_api.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car")
public class Car extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "train_id", referencedColumnName = "id")
    private Train train;

    private String type;

    @Column(unique = true)
    private String code;

    private double price;
    private byte numSeat;

}

package com.example.rail_e_ticket_api.ticket_car_owner.entity;

import com.example.rail_e_ticket_api.entity.TrainDestination;
import com.example.rail_e_ticket_api.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticket")
public class Ticket extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "train_destination_id", referencedColumnName = "id")
    private TrainDestination trainDestination;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    private byte numSeat;

}

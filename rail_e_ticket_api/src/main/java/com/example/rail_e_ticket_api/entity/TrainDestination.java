package com.example.rail_e_ticket_api.entity;

import com.example.rail_e_ticket_api.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "train_destination")
public class TrainDestination extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "train_id", referencedColumnName = "id")
    private Train train;

    @ManyToOne
    @JoinColumn(name = "from_station_id", referencedColumnName = "id")
    private Station fromStation;

    @ManyToOne
    @JoinColumn(name = "to_station_id", referencedColumnName = "id")
    private Station toStation;

    @Column(name = "departure_date", nullable = false)
    private Date departureDate;

    @Column(name = "arrive_date", nullable = false)
    private Date arriveDate;

    @Column(name = "directions_code")
    private int directionsCode;


}

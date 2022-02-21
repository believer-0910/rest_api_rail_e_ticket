package com.example.rail_e_ticket_api.entity;

import com.example.rail_e_ticket_api.entity.base.BaseEntity;
import lombok.*;
import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @JoinColumn(name = "price_id", referencedColumnName = "id")
    private Price price;

    @Column(name = "departure_date", nullable = false)
    private Date departureDate;

    @Column(name = "arrive_date", nullable = false)
    private Date arriveDate;


}

package com.example.rail_e_ticket_api.ticket_car_owner.entity;

import com.example.rail_e_ticket_api.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "owner")
public class Owner extends BaseEntity {
    private String name;

    @Column(unique = true)
    private String userName;

    private String password;
    private int userRole;

}

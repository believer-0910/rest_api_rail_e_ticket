package com.example.rail_e_ticket_api.ticket_car_owner.repository;

import com.example.rail_e_ticket_api.ticket_car_owner.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {
    Optional<Car> findByCode(String code);
}

package com.example.rail_e_ticket_api.repository;

import com.example.rail_e_ticket_api.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByCode(String code);
}

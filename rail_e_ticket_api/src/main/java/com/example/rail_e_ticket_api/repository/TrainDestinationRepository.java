package com.example.rail_e_ticket_api.repository;

import com.example.rail_e_ticket_api.entity.Station;
import com.example.rail_e_ticket_api.entity.TrainDestination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.management.OperatingSystemMXBean;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TrainDestinationRepository extends JpaRepository<TrainDestination, Long> {
    Optional<TrainDestination> findByDepartureDateAndArriveDate(LocalDateTime departureDate, LocalDateTime arriveDate);
    @Query(value = "select distinct * from train_destination where directions_code & ?1 <> 0 " +
            "and direction_code & ?2 and ?2 > ?1 and departure_date::date = ?3 order by created_date", nativeQuery = true)
    List<TrainDestination> getTrainsFromTo(int fromCode, int toCode, LocalDate date);

    List<TrainDestination> findAllByStation_Destination_Code(int station_destination_code);
}

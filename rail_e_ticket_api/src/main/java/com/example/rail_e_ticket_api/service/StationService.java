package com.example.rail_e_ticket_api.service;

import com.example.rail_e_ticket_api.payload.StationDto;
import com.example.rail_e_ticket_api.entity.Station;
import com.example.rail_e_ticket_api.exception.CustomException;
import com.example.rail_e_ticket_api.repository.StationRepository;
import com.example.rail_e_ticket_api.response.ApiResponse;
import com.example.rail_e_ticket_api.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.rail_e_ticket_api.util.interfaces.ResponseConstants.*;

@RequiredArgsConstructor
@Service
public class StationService implements BaseService<StationDto> {

    private final StationRepository stationRepository;
    private final ModelMapper mapper;


    @Override
    public ApiResponse add(StationDto stationDto) {
        checkStation(stationDto.getDestination().getId(), stationDto.getName());
        Station station = mapper.map(stationDto, Station.class);
        stationRepository.save(station);
        return new ApiResponse(SUCCESS, 201, station);
    }

    @Override
    public ApiResponse getById(UUID id) {
        Optional<Station> station = stationRepository.findById(id);
        if (station.isPresent())
            return new ApiResponse(SUCCESS, 201, station.get());
        throw new CustomException(NOT_FOUND);
    }

    @Override
    public ApiResponse getList() {
        List<Station> stationList = stationRepository.findAll();
        return stationList.isEmpty() ? new ApiResponse(NOT_FOUND, 404)
                : new ApiResponse(SUCCESS, 200, stationList);
    }

    @Override
    public ApiResponse updateById(UUID id, StationDto stationDto) {
        Optional<Station> optionalStation = stationRepository.findById(id);
        if (optionalStation.isPresent()) {
            Station station = mapper.map(stationDto, Station.class);
            station.setId(id);
            stationRepository.save(station);
            return new ApiResponse(SUCCESS, 202, station);
        }
        throw new CustomException(NOT_FOUND);
    }

    @Override
    public ApiResponse deleteById(UUID id) {
        Optional<Station> station = stationRepository.findById(id);
        if (station.isPresent()) {
            stationRepository.delete(station.get());
            return new ApiResponse(SUCCESS, 204, station.get());
        }
        throw new CustomException(NOT_FOUND);
    }

    protected void checkStation(UUID destinationId, String name) {
        Optional<Station> byDestinationIdAndName = stationRepository.findByDestinationIdAndName(destinationId, name);
        if (byDestinationIdAndName.isPresent())
            throw new CustomException("Station already exist with destination id: " + destinationId + " and name: " + name);
    }
}

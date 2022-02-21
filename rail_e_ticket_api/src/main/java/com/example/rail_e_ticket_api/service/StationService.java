package com.example.rail_e_ticket_api.service;

import com.example.rail_e_ticket_api.dto.StationDto;
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

import static com.example.rail_e_ticket_api.constants.ResponseConstants.*;

@RequiredArgsConstructor
@Service
public class StationService implements BaseService<StationDto> {

    private final StationRepository stationRepository;
    private final ModelMapper mapper;


    @Override
    public ApiResponse add(StationDto stationDto) {
        checkStation(stationDto.getDestination().getId(), stationDto.getName());
        Station station = mapper.map(stationDto, Station.class);
        Station savedStation = stationRepository.save(station);
        return new ApiResponse(SUCCESS, 200, savedStation);
    }

    @Override
    public ApiResponse getById(Long id) {
        Optional<Station> station = stationRepository.findById(id);
        if (station.isPresent())
            return new ApiResponse(SUCCESS, 200, station.get());
        throw new CustomException(NOT_FOUND);
    }

    @Override
    public ApiResponse getList() {
        List<Station> stationList = stationRepository.findAll();
        return new ApiResponse(SUCCESS, 200, stationList);
    }

    @Override
    public ApiResponse updateById(Long id, StationDto stationDto) {
        Optional<Station> station = stationRepository.findById(id);
        if (station.isPresent()){
            Station station1 = mapper.map(stationDto, Station.class);
            station1.setId(id);
            stationRepository.save(station1);
            return new ApiResponse(SUCCESS, 200, station1);
        }
        throw new CustomException(NOT_FOUND);
    }

    @Override
    public ApiResponse deleteById(Long id) {
        Optional<Station> station = stationRepository.findById(id);
        if (station.isPresent()){
            stationRepository.delete(station.get());
            return new ApiResponse(SUCCESS, 200, station.get());
        }
        throw new CustomException(NOT_FOUND);
    }

    protected void checkStation(Long destinationId, String name){
        Optional<Station> byDestinationIdAndName = stationRepository.findByDestinationIdAndName(destinationId, name);
        if (byDestinationIdAndName.isPresent())
            throw new CustomException("Station already exist with destination id: " + destinationId + " and name: " + name);
    }
}

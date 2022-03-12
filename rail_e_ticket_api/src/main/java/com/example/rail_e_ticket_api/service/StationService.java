package com.example.rail_e_ticket_api.service;

import com.example.rail_e_ticket_api.entity.Station;
import com.example.rail_e_ticket_api.exception.CustomException;
import com.example.rail_e_ticket_api.payload.StationDto;
import com.example.rail_e_ticket_api.repository.DestinationRepository;
import com.example.rail_e_ticket_api.repository.StationRepository;
import com.example.rail_e_ticket_api.response.ApiResponse;
import com.example.rail_e_ticket_api.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.rail_e_ticket_api.util.interfaces.ResponseConstants.NOT_FOUND;
import static com.example.rail_e_ticket_api.util.interfaces.ResponseConstants.SUCCESS;

@RequiredArgsConstructor
@Service
public class StationService implements BaseService<StationDto> {

    private final StationRepository stationRepository;
    private final DestinationRepository destinationRepository;
    private final ModelMapper mapper;


    @Override
    public ApiResponse add(StationDto stationDto) {
        checkStation(stationDto.getDestination().getId(), stationDto.getName());
        stationDto.setDestination(destinationRepository.findById(stationDto.getDestination().getId()).get());
        Station station = mapper.map(stationDto, Station.class);
        stationRepository.save(station);
        return new ApiResponse(SUCCESS, 201, station);
    }

    @Override
    public ApiResponse getById(Long id) {
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
    public ApiResponse updateById(Long id, StationDto stationDto) {
        Optional<Station> optionalStation = stationRepository.findById(id);
        if (optionalStation.isPresent()) {
            Station station = optionalStation.get();
            station.setName(stationDto.getName());
            station.setDestination(destinationRepository.findById(stationDto.getDestination().getId()).get());
            return new ApiResponse(SUCCESS, 202, stationRepository.save(station));
        }
        throw new CustomException(NOT_FOUND);
    }

    @Override
    public ApiResponse deleteById(Long id) {
        Optional<Station> station = stationRepository.findById(id);
        if (station.isPresent()) {
            stationRepository.delete(station.get());
            return new ApiResponse(SUCCESS, 204, station.get());
        }
        throw new CustomException(NOT_FOUND);
    }

    protected void checkStation(Long destinationId, String name) {
        Optional<Station> byDestinationIdAndName = stationRepository.findByDestinationIdAndName(destinationId, name);
        if (byDestinationIdAndName.isPresent())
            throw new CustomException("Station already exist with destination id: " + destinationId + " and name: " + name);
    }

    public List<Station> getListByDestinationId(Long id) {
        return stationRepository.findStationsByDestinationId(id);
    }
}

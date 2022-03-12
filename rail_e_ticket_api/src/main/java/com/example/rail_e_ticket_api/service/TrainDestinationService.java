package com.example.rail_e_ticket_api.service;

import com.example.rail_e_ticket_api.entity.Station;
import com.example.rail_e_ticket_api.entity.Train;
import com.example.rail_e_ticket_api.payload.TrainDestinationDto;
import com.example.rail_e_ticket_api.entity.TrainDestination;
import com.example.rail_e_ticket_api.exception.CustomException;
import com.example.rail_e_ticket_api.payload.TrainSearchRequestDTO;
import com.example.rail_e_ticket_api.payload.TrainSearchResponseDTO;
import com.example.rail_e_ticket_api.repository.TrainDestinationRepository;
import com.example.rail_e_ticket_api.response.ApiResponse;
import com.example.rail_e_ticket_api.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.rail_e_ticket_api.util.interfaces.ResponseConstants.*;

@Service
@RequiredArgsConstructor
public class TrainDestinationService implements BaseService<TrainDestinationDto> {

    private final TrainDestinationRepository trainDestinationRepository;
    private final ModelMapper mapper;
    private final StationService stationService;

    @Override
    public ApiResponse add(TrainDestinationDto trainDestinationDto) {
        checkTrainDestination(trainDestinationDto.getDepartureDate(), trainDestinationDto.getArriveDate());
        TrainDestination trainDestination = mapper.map(trainDestinationDto, TrainDestination.class);
        TrainDestination trainDestination1 = trainDestinationRepository.save(trainDestination);
        return new ApiResponse(SUCCESS, 200, trainDestination1);
    }

    @Override
    public ApiResponse getById(Long id) {
        Optional<TrainDestination> trainDestination = trainDestinationRepository.findById(id);
        if (trainDestination.isPresent()){
            return new ApiResponse(SUCCESS, 200, trainDestination.get());
        }
        throw new CustomException(NOT_FOUND);
    }

    @Override
    public ApiResponse getList() {
        List<TrainDestination> trainDestinationList = trainDestinationRepository.findAll();
        return trainDestinationList.isEmpty() ? new ApiResponse(NOT_FOUND, 404)
                : new ApiResponse(SUCCESS, 200, trainDestinationList);
    }

    @Override
    public ApiResponse updateById(Long id, TrainDestinationDto trainDestinationDto) {
        Optional<TrainDestination> trainDestination = trainDestinationRepository.findById(id);
        if (trainDestination.isPresent()){
            TrainDestination trainDestination1 = mapper.map(trainDestinationDto, TrainDestination.class);
            trainDestination1.setId(id);
            return new ApiResponse(SUCCESS, 200, trainDestination1);
        }
        throw new CustomException(NOT_FOUND);
    }

    @Override
    public ApiResponse deleteById(Long id) {
        Optional<TrainDestination> trainDestinationRepositoryById = trainDestinationRepository.findById(id);
        if (trainDestinationRepositoryById.isPresent()){
            trainDestinationRepository.delete(trainDestinationRepositoryById.get());
            return new ApiResponse(SUCCESS, 200, trainDestinationRepositoryById.get());
        }
        throw new CustomException(NOT_FOUND);
    }

    protected void checkTrainDestination(LocalDateTime departureDate, LocalDateTime arriveDate){
        Optional<TrainDestination> byDepartureDateAndArriveDateAndPriceId =
                trainDestinationRepository.findByDepartureDateAndArriveDate(departureDate, arriveDate);
        if (byDepartureDateAndArriveDateAndPriceId.isPresent())
            throw new CustomException("Train is already is added this time: " + departureDate);
    }

    public ApiResponse getTrainsBySearch(TrainSearchRequestDTO trainSearchRequestDTO) {
        List<TrainSearchResponseDTO> trains = new ArrayList<>();
        int fromDestinationCode = trainSearchRequestDTO.getFromDestinationCode();
        int toDestinationCode = trainSearchRequestDTO.getToDestinationCode();
        LocalDate date = trainSearchRequestDTO.getDate();
        List<TrainDestination> fromTrains = trainDestinationRepository.findAllByStation_Destination_Code(fromDestinationCode);
        List<TrainDestination> toTrains = trainDestinationRepository.findAllByStation_Destination_Code(toDestinationCode);
        for (TrainDestination fromTrain : fromTrains) {
            Train train1 = fromTrain.getTrain();
            if (fromTrain.getDepartureDate().toLocalDate().equals(date)) {
                for (TrainDestination toTrain : toTrains) {
                    Train train2 = toTrain.getTrain();
                    if (fromTrain.getStation().getDestination().getCode() < toTrain.getStation().getDestination().getCode() &&
                    train1.equals(train2)) {
                        TrainSearchResponseDTO train = new TrainSearchResponseDTO();
                        train.setId(train1.getId());
                        train.setTrainCode(train1.getCode());
                        train.setTrainType(train1.getType());
                        train.setFromDestination(fromTrain.getFromStation().getDestination().getName());
                        train.setToDestination(fromTrain.getToStation().getDestination().getName());
                        train.setFromStation(fromTrain.getStation().getName());
                        LocalDateTime departureTime = fromTrain.getDepartureDate();
                        LocalDateTime arrivalTime = toTrain.getArriveDate();
                        long between = ChronoUnit.MINUTES.between(arrivalTime, departureTime);
                        train.setDepartureTime(departureTime);
                        train.setToStation(toTrain.getStation().getName());
                        train.setArrivalTime(arrivalTime);
                        train.setDepartureTime(between);
                        trains.add(train);
                    }
                }
            }
        }
        return new ApiResponse(SUCCESS, 200, trains);
    }
}

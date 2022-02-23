package com.example.rail_e_ticket_api.service;

import com.example.rail_e_ticket_api.payload.DestinationDto;
import com.example.rail_e_ticket_api.entity.Destination;
import com.example.rail_e_ticket_api.exception.CustomException;
import com.example.rail_e_ticket_api.repository.DestinationRepository;
import com.example.rail_e_ticket_api.response.ApiResponse;
import com.example.rail_e_ticket_api.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.rail_e_ticket_api.util.interfaces.ResponseConstants.*;

@RequiredArgsConstructor
@Service
public class DestinationService implements BaseService<DestinationDto> {

    private final DestinationRepository destinationRepository;

    private final ModelMapper mapper;

    @Override
    public ApiResponse add(DestinationDto destinationDto) {
        checkDestination(destinationDto.getCode());
        Destination destination = mapper.map(destinationDto, Destination.class);
        destinationRepository.save(destination);
        return new ApiResponse(SUCCESS, 201, destination);
    }

    @Override
    public ApiResponse getById(Long id) {
        Optional<Destination> destination = destinationRepository.findById(id);
        if (destination.isPresent()) {
            return new ApiResponse(SUCCESS, 200, destination.get());
        }
        throw new CustomException(NOT_FOUND);
    }

    @Override
    public ApiResponse getList() {
        List<Destination> destinationList = destinationRepository.findAll();
        return destinationList.isEmpty()?new ApiResponse(NOT_FOUND,404)
        :new ApiResponse(SUCCESS, 200, destinationList);
    }

    @Override
    public ApiResponse updateById(Long id, DestinationDto destinationDto) {
        Optional<Destination> destinationOptional = destinationRepository.findById(id);
        if (destinationOptional.isPresent()) {
            Destination destination = mapper.map(destinationDto, Destination.class);
            destination.setId(destinationOptional.get().getId());
            return new ApiResponse(SUCCESS, 200, destination);
        }
        throw new CustomException(NOT_FOUND);
    }

    @Override
    public ApiResponse deleteById(Long id) {
        Optional<Destination> destination = destinationRepository.findById(id);
        if (destination.isPresent()) {
            destinationRepository.delete(destination.get());
            return new ApiResponse(SUCCESS, 200, destination.get());
        }
        throw new CustomException(NOT_FOUND);
    }

    private void checkDestination(String code) {
        if (destinationRepository.findByCode(code).isPresent())
            throw new CustomException(ALREADY_EXIST);
    }
}

package com.example.rail_e_ticket_api.service;

import com.example.rail_e_ticket_api.dto.DestinationDto;
import com.example.rail_e_ticket_api.entity.Destination;
import com.example.rail_e_ticket_api.exception.CustomException;
import com.example.rail_e_ticket_api.repository.DestinationRepository;
import com.example.rail_e_ticket_api.response.ApiResponse;
import com.example.rail_e_ticket_api.response.BaseResponse;
import com.example.rail_e_ticket_api.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return BaseResponse.SUCCESS;
    }

    @Override
    public ApiResponse getById(Long id) {
        Optional<Destination> destination = destinationRepository.findById(id);
        if (destination.isPresent()){
            ApiResponse apiResponse = new ApiResponse("success", 200);
            apiResponse.setObject(destination);
            return apiResponse;
        }
        throw new CustomException("Destination not found with this id: " + id);
    }

    @Override
    public ApiResponse getList() {
        List<Destination> destinationList = destinationRepository.findAll();
        ApiResponse apiResponse = new ApiResponse("success", 200);
        apiResponse.setObject(destinationList);
        return apiResponse;
    }

    @Override
    public ApiResponse updateById(Long id, DestinationDto destinationDto) {
        Optional<Destination> destinationOptional = destinationRepository.findById(id);
        if (destinationOptional.isPresent()){
            Destination destination = mapper.map(destinationDto, Destination.class);
            destination.setId(destinationOptional.get().getId());
            ApiResponse apiResponse = new ApiResponse("success", 200);
            apiResponse.setObject(destination);
            return apiResponse;
        }
        throw new CustomException("Destination not found with this id: " + id);
    }

    @Override
    public ApiResponse deleteById(Long id) {
        Optional<Destination> destination = destinationRepository.findById(id);
        if (destination.isPresent()){
            destinationRepository.delete(destination.get());
            ApiResponse apiResponse = new ApiResponse("success", 200);
            apiResponse.setObject(destination);
            return apiResponse;
        }
        throw new CustomException("Destination not found with this id: " + id);
    }

    private void checkDestination(String code){
        Optional<Destination> destinationByCode = destinationRepository.findDestinationByCode(code);

        if (destinationByCode.isPresent())
            throw new CustomException("Destination with this " + code + " code is already exist");
    }
}

package com.example.rail_e_ticket_api.ticket_car_owner.service;

import com.example.rail_e_ticket_api.entity.Destination;
import com.example.rail_e_ticket_api.exception.CustomException;
import com.example.rail_e_ticket_api.response.ApiResponse;
import com.example.rail_e_ticket_api.service.base.BaseService;
import com.example.rail_e_ticket_api.ticket_car_owner.dto.OwnerDto;
import com.example.rail_e_ticket_api.ticket_car_owner.entity.Owner;
import com.example.rail_e_ticket_api.ticket_car_owner.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.rail_e_ticket_api.constants.ResponseConstants.*;

@RequiredArgsConstructor
@Service
public class OwnerService implements BaseService<OwnerDto> {

    private final OwnerRepository ownerRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse add(OwnerDto ownerDto) {
        checkOwner(ownerDto.getUserName());
        Owner owner = modelMapper.map(ownerDto, Owner.class);
        ownerRepository.save(owner);
        return new ApiResponse(SUCCESS, 200, owner);
    }

    @Override
    public ApiResponse getById(Long id) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        if (optionalOwner.isPresent())
            return new ApiResponse(SUCCESS, 200, optionalOwner.get());
        throw new CustomException(NOT_FOUND);
    }

    @Override
    public ApiResponse getList() {
        List<Owner> ownerList = ownerRepository.findAll();
        return new ApiResponse(SUCCESS, 200, ownerList);
    }

    @Override
    public ApiResponse updateById(Long id, OwnerDto ownerDto) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        if (optionalOwner.isPresent()){
            Owner owner = modelMapper.map(ownerDto, Owner.class);
            owner.setId(id);
            ownerRepository.save(owner);
            return new ApiResponse(SUCCESS, 200, owner);
        }
        throw new CustomException(NOT_FOUND);
    }

    @Override
    public ApiResponse deleteById(Long id) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        if (optionalOwner.isPresent()){
            ownerRepository.deleteById(optionalOwner.get().getId());
            return new ApiResponse(SUCCESS, 200, optionalOwner.get());
        }
        throw new CustomException(NOT_FOUND);
    }

    private void checkOwner(String username) {
        Optional<Owner> optionalOwner = ownerRepository.findByUserName(username);
        if (optionalOwner.isPresent())
            throw new CustomException(ALREADY_EXIST);
    }
}

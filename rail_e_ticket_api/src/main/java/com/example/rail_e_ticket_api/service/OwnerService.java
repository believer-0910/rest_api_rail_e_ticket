package com.example.rail_e_ticket_api.service;

import com.example.rail_e_ticket_api.component.EmailComponent;
import com.example.rail_e_ticket_api.exception.CustomException;
import com.example.rail_e_ticket_api.payload.LoginDto;
import com.example.rail_e_ticket_api.response.ApiResponse;
import com.example.rail_e_ticket_api.service.base.BaseService;
import com.example.rail_e_ticket_api.payload.OwnerDto;
import com.example.rail_e_ticket_api.entity.Owner;
import com.example.rail_e_ticket_api.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.rail_e_ticket_api.util.interfaces.ResponseConstants.*;

@RequiredArgsConstructor
@Service
public class OwnerService implements BaseService<OwnerDto> {

    private final OwnerRepository ownerRepository;
    private final ModelMapper modelMapper;
    private final EmailComponent emailComponent;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse add(OwnerDto ownerDto) {
        checkOwner(ownerDto.getUsername());
        ownerDto.setPassword(passwordEncoder.encode(ownerDto.getPassword()));
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

    public ApiResponse getByUsername(String username) {
        Optional<Owner> optionalOwner = ownerRepository.findOwnerByUsernameOrEmail(username);
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
        if (optionalOwner.isPresent()) {
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
        if (optionalOwner.isPresent()) {
            ownerRepository.deleteById(optionalOwner.get().getId());
            return new ApiResponse(SUCCESS, 200, optionalOwner.get());
        }
        throw new CustomException(NOT_FOUND);
    }

    private void checkOwner(String username) {
        Optional<Owner> optionalOwner = ownerRepository.findByUsernameAndActive(username, true);
        if (optionalOwner.isPresent())
            throw new CustomException(ALREADY_EXIST);
    }

    public ApiResponse checkOwnerAndSendVerification(OwnerDto ownerDto) {
        if (ownerRepository.existsByUsernameOrEmail(ownerDto.getUsername(), ownerDto.getEmail()))
            return new ApiResponse(ALREADY_EXIST, 409, false);
        ownerDto.setPassword(passwordEncoder.encode(ownerDto.getPassword()));
        ownerRepository.save(modelMapper.map(ownerDto, Owner.class));
        new Thread(() -> emailComponent.sendToRegistration(ownerDto.getEmail())).start();
        return new ApiResponse(SUCCESS_REGISTER, 200, true);
    }

    public ApiResponse verifyOwner(String email) {
        Optional<Owner> optionalOwner = ownerRepository.findByEmailAndActive(email, false);
        if (optionalOwner.isPresent()) {
            Owner owner = optionalOwner.get();
            owner.setActive(true);
            ownerRepository.save(owner);
            return new ApiResponse(SUCCESS, 201, true);
        }
        return new ApiResponse(NOT_FOUND, 404, false);
    }
}

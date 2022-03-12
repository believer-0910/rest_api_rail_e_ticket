package com.example.rail_e_ticket_api.service;

import com.example.rail_e_ticket_api.payload.PriceDto;
import com.example.rail_e_ticket_api.entity.Price;
import com.example.rail_e_ticket_api.exception.CustomException;
import com.example.rail_e_ticket_api.repository.PriceRepository;
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
public class PriceService implements BaseService<PriceDto> {

    private final PriceRepository priceRepository;
    private final ModelMapper mapper;


    @Override
    public ApiResponse add(PriceDto priceDto) {
        checkPrice(priceDto.getFromStation().getId(), priceDto.getToStation().getId());
        return new ApiResponse(SUCCESS, 201, mapper.map(priceDto, Price.class));
    }

    @Override
    public ApiResponse getById(Long id) {
        Optional<Price> priceOptional = priceRepository.findById(id);
        if (priceOptional.isPresent()){
            return new ApiResponse(SUCCESS, 200, priceOptional.get());
        }
        throw new CustomException(NOT_FOUND);
    }

    @Override
    public ApiResponse getList() {
        List<Price> priceList = priceRepository.findAll();
        return priceList.isEmpty()?new ApiResponse(NOT_FOUND,404)
        :new ApiResponse(SUCCESS, 200, priceList);
    }

    @Override
    public ApiResponse updateById(Long id, PriceDto priceDto) {
        Optional<Price> priceRepositoryById = priceRepository.findById(id);
        if (priceRepositoryById.isPresent()){
            Price price = mapper.map(priceDto, Price.class);
            price.setId(id);
            priceRepository.save(price);
            return new ApiResponse(SUCCESS, 202, price);
        }
        throw new CustomException(NOT_FOUND);
    }

    @Override
    public ApiResponse deleteById(Long id) {
        Optional<Price> priceOptional = priceRepository.findById(id);
        if (priceOptional.isPresent()){
            priceRepository.delete(priceOptional.get());
            return new ApiResponse(SUCCESS, 204, priceOptional.get());
        }
        throw new CustomException(NOT_FOUND);
    }

    protected void checkPrice(Long fromStationId, Long toStationId){
        if (priceRepository.findByFromStationIdAndToStationId(fromStationId, toStationId).isPresent())
            throw new CustomException(ALREADY_EXIST);
    }
}

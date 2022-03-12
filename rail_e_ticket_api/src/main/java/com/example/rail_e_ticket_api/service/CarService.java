package com.example.rail_e_ticket_api.service;

import com.example.rail_e_ticket_api.exception.CustomException;
import com.example.rail_e_ticket_api.repository.TrainRepository;
import com.example.rail_e_ticket_api.response.ApiResponse;
import com.example.rail_e_ticket_api.service.base.BaseService;
import com.example.rail_e_ticket_api.payload.CarDto;
import com.example.rail_e_ticket_api.entity.Car;
import com.example.rail_e_ticket_api.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.rail_e_ticket_api.util.interfaces.ResponseConstants.NOT_FOUND;
import static com.example.rail_e_ticket_api.util.interfaces.ResponseConstants.SUCCESS;

@RequiredArgsConstructor
@Service
public class CarService implements BaseService<CarDto> {

    private final CarRepository carRepository;
    private final TrainRepository trainRepository;

    @Override
    public ApiResponse add(CarDto carDto) {
        checkCar(carDto.getCode());
        Car car = new Car(
                trainRepository.findById(carDto.getTrainId()).get(),
                carDto.getType(),
                carDto.getCode(),
                carDto.getPrice(),
                carDto.getNumSeats()
        );
        carRepository.save(car);
        return new ApiResponse(SUCCESS, 200, car);
    }

    @Override
    public ApiResponse getById(Long id) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent())
            return new ApiResponse(SUCCESS, 200, carOptional.get());
        throw new CustomException(NOT_FOUND);
    }

    @Override
    public ApiResponse getList() {
        List<Car> carList = carRepository.findAll();
        return new ApiResponse(SUCCESS, 200, carList);
    }

    @Override
    public ApiResponse updateById(Long id, CarDto carDto) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            car.setTrain(trainRepository.findById(carDto.getTrainId()).get());
            car.setType(carDto.getType());
            car.setCode(carDto.getCode());
            car.setPrice(carDto.getPrice());
            car.setNumSeats(carDto.getNumSeats());
            carRepository.save(car);
            return new ApiResponse(SUCCESS, 200, car);
        }
        throw new CustomException(NOT_FOUND);
    }

    @Override
    public ApiResponse deleteById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            carRepository.deleteById(optionalCar.get().getId());
            return new ApiResponse(SUCCESS, 200, optionalCar.get());
        }
        throw new CustomException(NOT_FOUND);
    }

    private void checkCar(String code) {
        Optional<Car> carOptional = carRepository.findByCode(code);

        if (carOptional.isPresent())
            throw new CustomException("Car with this " + code + " code is already exist");
    }
}

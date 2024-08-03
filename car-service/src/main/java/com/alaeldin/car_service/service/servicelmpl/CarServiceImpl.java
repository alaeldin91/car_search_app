package com.alaeldin.car_service.service.servicelmpl;

import com.alaeldin.car_service.dto.CarDto;
import com.alaeldin.car_service.entity.Car;
import com.alaeldin.car_service.exception.resourcenotfound.ResourceNotFoundException;
import com.alaeldin.car_service.mapper.CarMapper;
import com.alaeldin.car_service.repository.CarRepository;
import com.alaeldin.car_service.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService
{
    private final CarRepository carRepository;
    @Override
    public String addCar(CarDto carDto) {

        Car car = CarMapper.mapToCar(carDto);
        carRepository.save(car);

        return "Successfully saved car in database.";
    }

    @Override
    public String updateCar(CarDto carDto) {

        Car existCar = carRepository.findById(carDto.getId())
                .orElseThrow(()
                        ->new ResourceNotFoundException("car","id"
                        ,(int)carDto.getId()));
        existCar.setId(carDto.getId());
        existCar.setLength(carDto.getLength());
        existCar.setWeight(carDto.getWeight());
        existCar.setVelocity(carDto.getVelocity());
        existCar.setColor(carDto.getColor());
        carRepository.save(existCar);
        return "Successfully updated car in database.";
    }

    @Override
    public void deleteCar(long carId)
    {
        Car car = carRepository.findById(carId)
                .orElseThrow(()
                        ->new ResourceNotFoundException("car","id",(int)carId));
        carRepository.delete(car);
    }

    @Override
    public List<CarDto> getAllCar()
    {
        List<Car> cars = carRepository.findAll();

        return cars.stream().map(CarMapper::mapToCarDto).toList();
    }

    @Override
    public CarDto getCarById(long carId) {

        Car car = carRepository.findById(carId)
                .orElseThrow(()->new ResourceNotFoundException("car","id"
                        ,(int)carId));

        return CarMapper.mapToCarDto(car);
    }
}

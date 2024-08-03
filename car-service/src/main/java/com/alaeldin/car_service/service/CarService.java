package com.alaeldin.car_service.service;

import com.alaeldin.car_service.dto.CarDto;
import com.alaeldin.car_service.entity.Car;

import java.util.List;

public interface CarService
{
     String addCar(CarDto carDto);
     String updateCar(CarDto carDto);
     void deleteCar(long carId);
     List<CarDto> getAllCar();
     CarDto getCarById(long carId);
}

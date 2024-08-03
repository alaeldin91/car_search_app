package com.alaeldin.car_search_service.service;

import com.alaeldin.car_service.dto.CarDto;
import com.alaeldin.car_service.entity.Car;

import java.util.List;

public interface CarSearchService
{

    List<CarDto> searchCars(CarDto carDto);
}

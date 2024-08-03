package com.alaeldin.car_search_service.controller;

import com.alaeldin.car_search_service.exception.CarsNotFoundException;
import com.alaeldin.car_search_service.service.servicelmpl.CarSearchServiceImpl;
import com.alaeldin.car_service.dto.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/search-car")
@RequiredArgsConstructor
public class CarSearchController
{
    private final CarSearchServiceImpl carSearchService;
    @PostMapping()
    public ResponseEntity<List<CarDto>> searchCars(@RequestBody CarDto carDto)
    {
        return new ResponseEntity<>(carSearchService
                .searchCars(carDto), HttpStatus.OK);
    }
    @ExceptionHandler(CarsNotFoundException.class)
    public ResponseEntity<String> handleCarsNotFoundException(CarsNotFoundException e) {

        return ResponseEntity.status(404).body(e.getMessage());
    }

}

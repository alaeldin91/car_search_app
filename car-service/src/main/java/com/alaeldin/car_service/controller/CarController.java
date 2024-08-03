package com.alaeldin.car_service.controller;

import com.alaeldin.car_service.dto.CarDto;
import com.alaeldin.car_service.service.servicelmpl.CarServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/cars")
@RequiredArgsConstructor
public class CarController
{
    private final CarServiceImpl carService;
    @PostMapping("add-car")
    public ResponseEntity<Map<String,String>> addCar(@RequestBody CarDto carDto)
   {
       Map<String, String> response = new HashMap<>();
       response.put("message", carService.addCar(carDto));
       HttpHeaders headers = new HttpHeaders();

       return new ResponseEntity<>(response,headers, HttpStatus.OK);
   }

    @PostMapping("update-car/{id}")
    public ResponseEntity<Map<String,String>>updateCar(@PathVariable("id") long id
            ,@RequestBody CarDto carDto)
    {
        carDto.setId(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", carService.updateCar(carDto));
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(response,headers,HttpStatus.OK);
    }

    @GetMapping("get-all-car")
    public ResponseEntity<List<CarDto>> getAllCar()
    {
         return new ResponseEntity<>
                 (carService.getAllCar(),HttpStatus.OK);
    }

    @GetMapping("get-car-by-id/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable("id") long id)
    {
        return new ResponseEntity<>
                (carService.getCarById(id),HttpStatus.OK);
    }

    @GetMapping("delete-car/{id}")
    public ResponseEntity<Map<String,String>> deleteCar(@PathVariable("id") long id)
    {
        carService.deleteCar(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Successfully deleted car in database.");
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(response,headers,HttpStatus.OK);
    }
}

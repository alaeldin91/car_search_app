package com.alaeldin.car_search_service.service.servicelmpl;

import com.alaeldin.car_search_service.exception.CarsNotFoundException;
import com.alaeldin.car_search_service.service.CarSearchService;
import com.alaeldin.car_service.dto.CarDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarSearchServiceImpl implements CarSearchService {

    private final WebClient webClient;

    @CircuitBreaker(name = "${spring.application.name}"
            ,fallbackMethod = "getDefaultSearchCars")
    @Override
    public List<CarDto> searchCars(CarDto carDto) {

        List<CarDto> cars = webClient.get()
                .uri("http://localhost:8080/api/v1/cars/get-all-car")
                .retrieve()
                .bodyToFlux(CarDto.class)
                .collectList().block();
        assert cars != null;
        List<CarDto> filteredCars = cars.stream()
                .filter(carDto1 -> carDto1.getLength() == carDto.getLength())
                .filter(carDto1 -> carDto1.getWeight() == carDto.getWeight())
                .filter(carDto1 -> carDto1.getVelocity() == carDto.getVelocity())
                .filter(carDto1 -> carDto1.getColor().equalsIgnoreCase(carDto.getColor()))
                .toList();
           System.out.println("ala"+" "+filteredCars);
        if (filteredCars.isEmpty()){

            throw  new CarsNotFoundException("No cars found matching the given criteria");

        }

        return filteredCars;
    }
    public List<CarDto> getDefaultSearchCars(CarDto carDto,Exception $e){

        CarDto defaultCardDto = new CarDto();
        defaultCardDto.setId(1);
        defaultCardDto.setLength(20);
        defaultCardDto.setWeight(50);
        defaultCardDto.setColor("yellow");
        defaultCardDto.setVelocity(20);
        List<CarDto> carDtoList = new ArrayList<>();
        carDtoList.add(defaultCardDto);

        return carDtoList;
    }
}

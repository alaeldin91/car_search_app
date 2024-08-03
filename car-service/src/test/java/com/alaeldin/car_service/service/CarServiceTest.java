package com.alaeldin.car_service.service;

import com.alaeldin.car_service.dto.CarDto;
import com.alaeldin.car_service.entity.Car;
import com.alaeldin.car_service.exception.resourcenotfound.ResourceNotFoundException;
import com.alaeldin.car_service.mapper.CarMapper;
import com.alaeldin.car_service.repository.CarRepository;
import com.alaeldin.car_service.service.servicelmpl.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CarServiceTest {

    @InjectMocks
    private CarServiceImpl carService;
    @Mock
    private CarRepository carRepository;
    private Car carEntity;

    @BeforeEach
    void setUp(){

        MockitoAnnotations.openMocks(this);
        carEntity = new Car(1L, 4, 5, 55, "Blue");
    }

    @Test
    void testGetCarById()
    {

        Car mockCar = new Car(1,20,30,40,"red");
        when(carRepository.findById(anyLong())).thenReturn(Optional.of(mockCar));
        CarDto carDto = carService.getCarById(1);
        assertEquals("red", carDto.getColor());
        verify(carRepository, times(1)).findById(1L);

    }

    @Test
    void testAddCar()
    {

        CarDto carDto = new CarDto(2,4,5,55,"Blue");
        Car car = CarMapper.mapToCar(carDto);
        when(carRepository.save(any(Car.class))).thenReturn(car);
        String saveCar = carService.addCar(carDto);
        assertEquals("Successfully saved car in database.",saveCar);
        verify(carRepository, times(1)).save(any(Car.class));

    }

    @Test
    void testGetAllCar()
    {
        List<Car> carList = Arrays.asList(
                new Car(1, 4.0, 1400, 180, "Red"),
                new Car(2, 4.5, 1500, 200, "Blue")
        );
        when(carRepository.findAll()).thenReturn(carList);
        List<CarDto> allCars = carService.getAllCar();
        List<CarDto> expectedCars = carList.stream()
                .map(CarMapper::mapToCarDto)
                .toList();
        assertEquals(expectedCars, allCars);
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testDeleteCarSuccess() {

        long carId = 1L;
        Car carEntity = new Car(carId, 4, 5, 55, "Blue");
        when(carRepository.findById(carId)).thenReturn(Optional.of(carEntity));
        doNothing().when(carRepository).delete(carEntity);
        carService.deleteCar(carId);
        verify(carRepository, times(1)).findById(carId);
        verify(carRepository, times(1)).delete(carEntity);
    }

    @Test
    void testDeleteCarNotFound() {
        long carId = 2L;
        when(carRepository.findById(carId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> carService.deleteCar(carId));
        verify(carRepository, times(1)).findById(carId);
        verify(carRepository, times(0)).delete(any(Car.class));
    }

    @Test
    void testUpdateCarSuccess() {

        CarDto carDto = new CarDto(1L, 4, 5, 55, "Red");
        when(carRepository.findById(carDto.getId())).thenReturn(Optional.of(carEntity));
        when(carRepository.save(any(Car.class))).thenReturn(carEntity);
        String result = carService.updateCar(carDto);
        assertEquals("Successfully updated car in database.", result);
        verify(carRepository, times(1)).save(any(Car.class));
    }

    @Test
    void testUpdateCarNotFound() {

        long carId = 2L;
        CarDto carDto = new CarDto(carId, 4, 5, 55, "Red");
        when(carRepository.findById(carId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, ()
                -> carService.updateCar(carDto));
        verify(carRepository, times(0)).save(any(Car.class));
    }
}

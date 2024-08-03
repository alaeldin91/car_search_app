package com.alaeldin.car_service.mapper;

import com.alaeldin.car_service.dto.CarDto;
import com.alaeldin.car_service.entity.Car;

public class CarMapper {

    /**
     * Maps a CarDto object to a Car object.
     *
     * @param carDto the CarDto object to be mapped
     * @return the resulting Car object
     */
    public static Car mapToCar(CarDto carDto)
    {
        Car car = new Car();
        car.setId(carDto.getId());
        car.setLength(carDto.getLength());
        car.setWeight(carDto.getWeight());
        car.setVelocity(carDto.getVelocity());
        car.setColor(carDto.getColor());

        return car;
    }

    /**
     * Maps a Car object to a CarDto object.
     *
     * @param car the Car object to be mapped
     * @return the resulting CarDto object
     */
    public static CarDto mapToCarDto(Car car)
    {
        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setLength(car.getLength());
        carDto.setWeight(car.getWeight());
        carDto.setVelocity(car.getVelocity());
        carDto.setColor(car.getColor());

        return  carDto;
    }
}

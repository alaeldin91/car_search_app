package com.alaeldin.dowanload_car_service.dto;

import com.alaeldin.car_service.dto.CarDto;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
public class CarDtoListWrapper {

    private List<CarDto> cars;

    public CarDtoListWrapper() {
    }

    public CarDtoListWrapper(List<CarDto> cars) {
        this.cars = cars;
    }

    @XmlElement(name = "car")
    public List<CarDto> getCars() {
        return cars;
    }

    public void setCars(List<CarDto> cars) {
        this.cars = cars;
    }
}

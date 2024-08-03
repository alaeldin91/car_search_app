package com.alaeldin.car_service.dto;

import jakarta.validation.constraints.NotEmpty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "car")
public class CarDto {

    private long id;
    @NotEmpty(message = "Field Length is Required")
    private double length;   // Length in Meter
    @NotEmpty(message = "Field Weight is Required")
    private double weight;   // Weight in kilograms
    @NotEmpty(message = "Field Velocity is Required")
    private double velocity; // Velocity in km/h

    @NotEmpty(message = "Field Color is Required")
    private String color;    // Color of the car

    public CarDto()
    {

    }

    public CarDto(long id, double length, double weight, double velocity, String color) {
        this.id = id;
        this.length = length;
        this.weight = weight;
        this.velocity = velocity;
        this.color = color;
    }

    @XmlAttribute
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @XmlElement(name = "length")
    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @XmlElement(name = "weight")
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @XmlElement(name = "velocity")
    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    @XmlElement(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarDto carDto = (CarDto) o;
        return id == carDto.id &&
                Double.compare(carDto.length, length) == 0 &&
                Double.compare(carDto.weight, weight) == 0 &&
                Double.compare(carDto.velocity, velocity) == 0 &&
                Objects.equals(color, carDto.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, length, weight, velocity, color);
    }
}

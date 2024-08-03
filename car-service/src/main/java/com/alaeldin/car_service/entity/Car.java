package com.alaeldin.car_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "cars")
public class Car
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @Column(name = "length",nullable = false)
    private double length;   // Length in Meter
    @Column(name = "weight",nullable = false)
    private double weight;   //weight in kilograms
    @Column(name = "velocity",nullable = false)
    private double velocity; // Velocity in km/h
    @Column(name = "color",nullable = false)
    private String color;   //color of the car

}

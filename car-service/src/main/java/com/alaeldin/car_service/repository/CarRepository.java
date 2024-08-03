package com.alaeldin.car_service.repository;

import com.alaeldin.car_service.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository  extends JpaRepository<Car,Long>
{

}

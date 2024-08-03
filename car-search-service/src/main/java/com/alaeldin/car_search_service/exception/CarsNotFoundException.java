package com.alaeldin.car_search_service.exception;

public class CarsNotFoundException extends RuntimeException
{
    public CarsNotFoundException(String message){
        super(message);
    }
}

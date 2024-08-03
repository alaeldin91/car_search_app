package com.alaeldin.dowanload_car_service.service;

import com.alaeldin.car_service.dto.CarDto;

import javax.xml.bind.JAXBException;

public interface DownloadCarService
{
    public String downloadCarsAsXML(CarDto searchCriteria) throws JAXBException;
}

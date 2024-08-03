package com.alaeldin.dowanload_car_service.service.ServiceImpl;

import com.alaeldin.car_search_service.exception.CarsNotFoundException;
import com.alaeldin.car_service.dto.CarDto;
import com.alaeldin.dowanload_car_service.dto.CarDtoListWrapper;
import com.alaeldin.dowanload_car_service.service.DownloadCarService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import javax.xml.bind.JAXBException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DowanloadCarServiceImpl implements DownloadCarService {

    private final WebClient webClient;

    @Override
    public String downloadCarsAsXML(CarDto searchCriteria) throws JAXBException {
        // Create some dummy car data
        List<CarDto> cars;

        try {

            cars = webClient.post()
                    .uri("http://localhost:8081/api/v1/search-car")
                    .bodyValue(searchCriteria)
                    .retrieve()
                    .bodyToFlux(CarDto.class)
                    .collectList()
                    .block();

            if (cars == null || cars.isEmpty()) {
                throw new CarsNotFoundException("No cars found for the given criteria.");
            }
            // Convert list to XML using Jackson
            XmlMapper xmlMapper = new XmlMapper();
            CarDtoListWrapper carDtoListWrapper = new CarDtoListWrapper(cars);
            return xmlMapper.writeValueAsString(carDtoListWrapper);
        } catch (Exception e) {
            throw new CarsNotFoundException("Error while fetching cars: " + e.getMessage());
        }
    }
    }


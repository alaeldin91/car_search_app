package com.alaeldin.car_search_service.service;


import com.alaeldin.car_search_service.exception.CarsNotFoundException;
import com.alaeldin.car_search_service.service.servicelmpl.CarSearchServiceImpl;
import com.alaeldin.car_service.dto.CarDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class CarSearchServiceTest {

    @Mock
    private  WebClient webClient;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;
    @InjectMocks
    private CarSearchServiceImpl carSearchService;
    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public  void testCarSearch()
    {
        CarDto searchCriteria = new CarDto(1, 4, 5, 55, "Blue");
        List<CarDto> carDtoList = List.
                of( new CarDto(1, 4, 5, 55, "Blue"),
                new CarDto(2, 4, 5, 55, "Blue"));
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(String.class))).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToFlux(CarDto.class)).thenReturn(Flux.fromIterable(carDtoList));
        List<CarDto> result = carSearchService.searchCars(searchCriteria);
        assertEquals(2, result.size());
    }

    @Test
    void testSearchCarsCarsNotFoundException()
    {
        CarDto searchCriteria = new CarDto(1, 4, 5, 55, "Red");
        List<CarDto> mockCars = List.of(
                new CarDto(1, 4, 5, 55, "Blue"));
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(String.class)))
                .thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToFlux(CarDto.class)).thenReturn(Flux
                .fromIterable(mockCars));
        assertThrows(CarsNotFoundException.class, ()
                -> carSearchService.searchCars(searchCriteria));
    }
}

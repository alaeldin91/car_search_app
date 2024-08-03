package com.alaeldin.dowanload_car_service.service;

import com.alaeldin.car_search_service.exception.CarsNotFoundException;
import com.alaeldin.car_service.dto.CarDto;
import com.alaeldin.dowanload_car_service.dto.CarDtoListWrapper;
import com.alaeldin.dowanload_car_service.service.ServiceImpl.DowanloadCarServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import javax.xml.bind.JAXBException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DowanloadCarServiceTest
{
    @Mock
    private WebClient webClient;
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;
    @Mock
    private WebClient.RequestBodySpec requestBodySpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private DowanloadCarServiceImpl downloadCarService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testDownloadCarsAsXML_Success() throws JAXBException, JsonProcessingException {
        CarDto searchCriteria = new CarDto
                (1, 4, 5, 55, "Blue");
        List<CarDto> mockCars = List.of(
                new CarDto(1, 4, 5, 55, "Blue"),
                new CarDto(2, 4, 5, 55, "Blue")
        );
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(any(String.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToFlux(CarDto.class)).thenReturn(Flux.fromIterable(mockCars));
        String result = downloadCarService.downloadCarsAsXML(searchCriteria);
        XmlMapper xmlMapper = new XmlMapper();
        CarDtoListWrapper expectedWrapper = new CarDtoListWrapper(mockCars);
        String expectedXml = xmlMapper.writeValueAsString(expectedWrapper);
        assertEquals(expectedXml, result);
    }

    @Test
    void testDownloadCarsAsXMLNoCarsFound() {

        CarDto searchCriteria = new CarDto
                (1, 4, 5, 55, "Blue");
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(any(String.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToFlux(CarDto.class)).thenReturn(Flux.empty());
        CarsNotFoundException exception = assertThrows(CarsNotFoundException.class, () -> {
            downloadCarService.downloadCarsAsXML(searchCriteria);
        });
        assertEquals("No cars found for the given criteria.", exception.getMessage());
    }

    @Test
    void testDownloadCarsAsXMLExceptionHandling() {
        CarDto searchCriteria = new CarDto(1, 4, 5, 55, "Blue");
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(any(String.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToFlux(CarDto.class)).thenReturn(Flux
                .error(new WebClientResponseException("Error", 500
                        , "Internal Server Error", null, null, null)));
        CarsNotFoundException exception = assertThrows(CarsNotFoundException.class, () -> {
            downloadCarService.downloadCarsAsXML(searchCriteria);
        });
        assertTrue(exception.getMessage().contains("Error while fetching cars"));
    }
}

package com.alaeldin.dowanload_car_service.Controller;

import com.alaeldin.car_service.dto.CarDto;
import com.alaeldin.dowanload_car_service.service.ServiceImpl.DowanloadCarServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping("api/v1/dowanload-cars")
@RequiredArgsConstructor
public class DownloadCarController
{
    private final DowanloadCarServiceImpl dowanloadCarService;
   @PostMapping()
   public ResponseEntity<String> dowanloadCarsAsXml(@RequestBody CarDto searchCriteria) throws JAXBException {

       return new ResponseEntity<>(dowanloadCarService
               .downloadCarsAsXML(searchCriteria)
               , HttpStatus.OK);
   }
}

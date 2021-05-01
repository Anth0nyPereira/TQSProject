package ua.deti.tqs.uv_tqs_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.deti.tqs.uv_tqs_project.AirQuality;
import ua.deti.tqs.uv_tqs_project.component.AirQualityStatistics;
import ua.deti.tqs.uv_tqs_project.service.AirQualityService;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class AirQualityController {

    @Autowired
    private AirQualityService aqService;

    @GetMapping("/data")
    public ResponseEntity<AirQuality> getData(@RequestParam String city) {
        //System.out.println("timeMilli: " + timeMilli);
        return new ResponseEntity<>(aqService.getData(city), HttpStatus.OK);
    }

    @GetMapping("/stats")
    public ResponseEntity<AirQualityStatistics> getStatistics() {
        return new ResponseEntity<>(aqService.getStats(), HttpStatus.OK);
    }

}
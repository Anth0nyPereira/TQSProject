package ua.deti.tqs.airquality_tqs_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.deti.tqs.airquality_tqs_project.AirQuality;
import ua.deti.tqs.airquality_tqs_project.component.AirQualityStatistics;
import ua.deti.tqs.airquality_tqs_project.component.City;
import ua.deti.tqs.airquality_tqs_project.service.AirQualityService;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AirQualityController {

    @Autowired
    private AirQualityService aqService;

    @GetMapping("/data")
    public ResponseEntity<Object> getData(@RequestParam String city) {
        AirQuality aq = aqService.getData(city);
        if (aq != null) {
            return new ResponseEntity<>(aq, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Could not find any data", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/stats")
    public ResponseEntity<Object> getStatistics() {
        AirQualityStatistics aqs = aqService.getStats();
        if (aqs != null) {
            return new ResponseEntity<>(aqs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Could not find any data", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/dataByCoords")
    public ResponseEntity<Object> getDataByCoords(@RequestParam double lat, @RequestParam double lon) {
        AirQuality aq = aqService.getDataByCoords(lat, lon);
        if (aq != null) {
            return new ResponseEntity<>(aq, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Could not find any data", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cache")
    public ResponseEntity<Object> getAllCitiesFromCache() {
        Map<City, AirQuality> cache = aqService.findAll();
        if (!cache.isEmpty()) {
            return new ResponseEntity<>(aqService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cache is empty", HttpStatus.NOT_FOUND);
        }
    }

}
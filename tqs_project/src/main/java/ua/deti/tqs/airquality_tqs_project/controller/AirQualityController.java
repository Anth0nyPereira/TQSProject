package ua.deti.tqs.airquality_tqs_project.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.deti.tqs.airquality_tqs_project.AirQuality;
import ua.deti.tqs.airquality_tqs_project.component.AirQualityLogs;
import ua.deti.tqs.airquality_tqs_project.component.AirQualityStatistics;
import ua.deti.tqs.airquality_tqs_project.City;
import ua.deti.tqs.airquality_tqs_project.service.AirQualityService;

import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api")
public class AirQualityController {

    @Autowired
    private AirQualityService aqService;

    @Autowired
    private AirQualityLogs logs;

    @GetMapping("/data") // endpoint to get AirQuality data by a city name
    public ResponseEntity<Object> getData(@RequestParam String city) {
        log.info("/api/data endpoint was accessed with parameter " + city);
        logs.addLog("/api/data endpoint was accessed with parameter " + city);
        if (city.equals("")) {
            return new ResponseEntity<>("Parameter was empty, so could not find any data", HttpStatus.BAD_REQUEST);
        }
        AirQuality aq = aqService.getData(city);
        if (aq != null) {
            return new ResponseEntity<>(aq, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Could not find any data", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/dataByCoords") // endpoint to get AirQuality data by geographical coordinates
    public ResponseEntity<Object> getDataByCoords(@RequestParam double lat, @RequestParam double lon) {
        log.info("/api/dataByCoords endpoint was accessed with parameters " + lat + " and " + lon);
        logs.addLog("/api/dataByCoords endpoint was accessed with parameters " + lat + " and " + lon);
        AirQuality aq = aqService.getDataByCoords(lat, lon);
        if (aq != null) {
            return new ResponseEntity<>(aq, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Could not find any data", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/stats") // endpoint to get cache statistics
    public ResponseEntity<Object> getStatistics() {
        log.info("/api/stats endpoint was accessed");
        logs.addLog("/api/stats endpoint was accessed");
        AirQualityStatistics aqs = aqService.getStats();
        if (aqs != null) {
            return new ResponseEntity<>(aqs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Could not find any data", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/cache") // endpoint to get content of cache atm
    public ResponseEntity<Object> getAllCitiesFromCache() {
        log.info("/api/cache endpoint was accessed");
        logs.addLog("/api/cache endpoint was accessed");
        Map<City, AirQuality> cache = aqService.findAll();
        if (!cache.isEmpty()) {
            return new ResponseEntity<>(aqService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cache is empty", HttpStatus.NOT_FOUND);
        }
    }

}
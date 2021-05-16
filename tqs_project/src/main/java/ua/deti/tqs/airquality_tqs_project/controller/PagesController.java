package ua.deti.tqs.airquality_tqs_project.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.deti.tqs.airquality_tqs_project.AirQuality;
import ua.deti.tqs.airquality_tqs_project.service.AirQualityService;


@Log4j2
@Controller
@RequestMapping("")
public class PagesController {

    @Autowired
    private AirQualityService service;

    @RequestMapping("") // mapping to the homepage
    public String Home() {
        log.info("Redirected to the homepage");
        return "index";
    }

    @RequestMapping("/info") // maping to the results page (by city name)
    public Object Data(@RequestParam String city) {
        if (city == "") { // when a user makes an empty search
            log.info("Redirected to the empty search page");
            return "emptySearch";
        } else if (!isNumeric(city)) { // checks if the string city has not numeric characters
            city = city.toLowerCase();
            city = city.substring(0, 1).toUpperCase() + city.substring(1);
            ModelAndView modelAndView = new ModelAndView("data"); // create model&View to "store" collected data and return new page
            AirQuality airQuality = service.getData(city);
            if (airQuality != null) {
                modelAndView.addObject("airquality", airQuality);
                modelAndView.addObject("city", city);
                log.info("Model And View Not Null Was Created Successfully");
                log.info("Redirected to the results page");
                return modelAndView;
            } else {
                log.info("Redirected to the error404 page");
                return "error404";
            }

        } else {
            log.info("Redirected to the error404 page");
            return "error404";
        }
    }

    @RequestMapping("/infoByCoords") // mapping to the results page (by geographical coordinates)
    public Object DataByCoords(@RequestParam String coordinates) {
        for (int x = 0; x < coordinates.length(); x++) { // check if the string has a comma (the string has a comma when the user uploads each coordinate separately; example: enter 40, press ENTER twice, then enter -40, then press Enter twice
            if (coordinates.charAt(x) == ',') {
                StringBuilder coordinatesSB = new StringBuilder(coordinates);
                coordinatesSB.setCharAt(x, ' '); // replace that comma with a whitespace
                coordinates = coordinatesSB.toString();
            }
        }

        String[] coordinatesArray = coordinates.split("\\s+"); // split string to get each coordinate

        if (coordinatesArray.length != 2) {
            log.info("Redirected to the error400 page");
            return "error400";
        }

        String lat = coordinatesArray[0];
        String lon = coordinatesArray[1];

        if (isDouble(lat) && isDouble(lon)) { // check if both are doubles
            double latitude = Double.parseDouble(lat);
            double longitude = Double.parseDouble(lon);
            if (latitude > 90 || latitude < -90 || longitude > 180 || longitude < -180) { // invalid latitude & longitude ranges
                log.info("Redirected to the error400 page");
                return "error400";
            } else {
                ModelAndView modelAndView = new ModelAndView("dataByCoords");
                AirQuality airQuality = service.getDataByCoords(latitude, longitude);
                if (airQuality != null) { // could collect data
                    String city = service.getCityNameByCoords(latitude, longitude);
                    modelAndView.addObject("airquality", airQuality);
                    modelAndView.addObject("latitude", latitude);
                    modelAndView.addObject("longitude", longitude);
                    modelAndView.addObject("city", city);
                    log.info("Model And View Not Null Was Created Successfully");
                    return modelAndView;
                } else {
                    log.info("Redirected to the error404 page");
                    return "error404";
                }
            }
        } else {
            log.info("Redirected to the error400 page");
            return "error400";
        }
    }
    // isNumeric method
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    // isDouble method
    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}

package ua.deti.tqs.airquality_tqs_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.deti.tqs.airquality_tqs_project.AirQuality;
import ua.deti.tqs.airquality_tqs_project.City;
import ua.deti.tqs.airquality_tqs_project.service.AirQualityService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("")
public class PagesController {

    @Autowired
    private AirQualityService service;

    @RequestMapping("")
    public String Home() {
        return "index";
    }

    @RequestMapping("/info")
    public Object Data(@RequestParam String city) {
        if (city == "") {
            return "emptySearch";
        } else if (!isNumeric(city)) {
            city = city.toLowerCase();
            city = city.substring(0, 1).toUpperCase() + city.substring(1);
            ModelAndView modelAndView = new ModelAndView("data");
            AirQuality airQuality = service.getData(city);
            System.out.println(airQuality);
            System.out.println(service.getStats());
            System.out.println(modelAndView);
            if (airQuality != null) {
                modelAndView.addObject("airquality", airQuality);
                modelAndView.addObject("city", city);
                return modelAndView;
            } else {
                return "error404";
            }

        } else {
            return "error404";
        }
    }

    @RequestMapping("/infoByCoords")
    public Object DataByCoords(@RequestParam String coordinates) {
        for (int x = 0; x < coordinates.length(); x++) {
            if (coordinates.charAt(x) == ',') {
                StringBuilder coordinatesSB = new StringBuilder(coordinates);
                coordinatesSB.setCharAt(x, ' ');
                coordinates = coordinatesSB.toString();
            }
        }

        String[] coordinatesArray = coordinates.split("\\s+");

        if (coordinatesArray.length != 2) {
            return "error400";
        }

        String lat = coordinatesArray[0];
        String lon = coordinatesArray[1];
        System.out.println("lat: " + lat);
        System.out.println("lon: " + lon);

        if (lat == "" || lon == "") {
            return "emptySearch";
        }
        if (isDouble(lat) && isDouble(lon)) {
            double latitude = Double.parseDouble(lat);
            double longitude = Double.parseDouble(lon);
            if (latitude > 90 || latitude < -90 || longitude > 180 || longitude < -180) {
                return "error400";
            } else {
                ModelAndView modelAndView = new ModelAndView("dataByCoords");
                AirQuality airQuality = service.getDataByCoords(latitude, longitude);
                if (airQuality != null) {
                    String city = service.getCityNameByCoords(latitude, longitude);
                    modelAndView.addObject("airquality", airQuality);
                    modelAndView.addObject("latitude", latitude);
                    modelAndView.addObject("longitude", longitude);
                    modelAndView.addObject("city", city);
                    return modelAndView;
                } else {
                    return "error404";
                }
            }
        } else {
            return "error400";
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}

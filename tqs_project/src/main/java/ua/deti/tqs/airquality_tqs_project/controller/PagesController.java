package ua.deti.tqs.airquality_tqs_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.deti.tqs.airquality_tqs_project.AirQuality;
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

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}

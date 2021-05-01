package ua.deti.tqs.uv_tqs_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.deti.tqs.uv_tqs_project.AirQuality;
import ua.deti.tqs.uv_tqs_project.service.AirQualityService;

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
    public ModelAndView Data(@RequestParam String city) {
        System.out.println(city);
        ModelAndView modelAndView = new ModelAndView("data");
        AirQuality airQuality = service.getData(city);
        System.out.println(airQuality);
        modelAndView.addObject("airquality", airQuality);
        return modelAndView;
    }
}

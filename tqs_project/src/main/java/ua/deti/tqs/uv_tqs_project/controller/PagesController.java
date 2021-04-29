package ua.deti.tqs.uv_tqs_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class PagesController {

    @RequestMapping("")
    public String Home() {
        return "index";
    }

    @RequestMapping("/data")
    public String Data() {
        return "data";
    }
}

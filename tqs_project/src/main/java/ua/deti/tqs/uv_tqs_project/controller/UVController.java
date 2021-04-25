package ua.deti.tqs.uv_tqs_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.deti.tqs.uv_tqs_project.service.UVService;
import ua.deti.tqs.uv_tqs_project.UVValue;

@RestController
@RequestMapping("/api/uv")
public class UVController {

    @Autowired
    private UVService uvservice;

    @GetMapping("")
    public ResponseEntity<UVValue> getUV(@RequestParam double latitude, @RequestParam double longitude) {
        return new ResponseEntity<>(uvservice.getUVValue(latitude, longitude), HttpStatus.OK);
    }

}

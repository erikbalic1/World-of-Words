package hu.unideb.inf.worldofwords.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CityController {

    @GetMapping("/testCities")
    List<String> allCities();

    @GetMapping("/testCity")
    boolean testCityExists(@RequestParam String city);

}

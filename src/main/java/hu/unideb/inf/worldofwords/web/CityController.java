package hu.unideb.inf.worldofwords.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/api")
public interface CityController {

    @GetMapping("/cities")
    ResponseEntity<List<String>> allCities();

    @GetMapping("/cities/exists")
    ResponseEntity<Boolean> checkCityExists(@RequestParam String name);

    @GetMapping("/cities/search")
    ResponseEntity<List<String>> searchCities(
            @RequestParam(required = false) String startingLetter,
            @RequestParam(required = false) String containing
    );

}

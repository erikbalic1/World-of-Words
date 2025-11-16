package hu.unideb.inf.worldofwords.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface CountryController {

    @GetMapping("/testCountries")
    List<String> allCountries();

    @GetMapping("/testCountry")
    boolean testCountryExists(@RequestParam String country);

}

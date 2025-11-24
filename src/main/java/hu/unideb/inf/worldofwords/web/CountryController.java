package hu.unideb.inf.worldofwords.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public interface CountryController {

    @GetMapping
    ResponseEntity<List<String>> allCountries();

    @GetMapping("/exists")
    ResponseEntity<Boolean> checkCountryExists(@RequestParam String name);

    @GetMapping("/search")
    ResponseEntity<List<String>> searchCountries(
            @RequestParam(required = false) String startingLetter,
            @RequestParam(required = false) String containing
    );

}

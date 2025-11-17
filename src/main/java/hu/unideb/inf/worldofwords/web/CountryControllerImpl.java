package hu.unideb.inf.worldofwords.web;

import hu.unideb.inf.worldofwords.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CountryControllerImpl implements CountryController {

    private final CountryService service;

    @Override
    public ResponseEntity<List<String>> allCountries() {
        return ResponseEntity.ok(service.allCountries());
    }

    @Override
    public ResponseEntity<Boolean> checkCountryExists(String name) {
        return ResponseEntity.ok(service.isValidCountry(name));
    }

    @Override
    public ResponseEntity<List<String>> searchCountries(String startingLetter, String containing) {
        return ResponseEntity.ok(service.searchCountries(startingLetter, containing));
    }

}

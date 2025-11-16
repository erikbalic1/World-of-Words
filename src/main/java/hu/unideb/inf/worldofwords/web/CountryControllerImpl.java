package hu.unideb.inf.worldofwords.web;

import hu.unideb.inf.worldofwords.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CountryControllerImpl implements CountryController {

    private final CountryService service;

    @Override
    public List<String> allCountries() {
        return service.allCountries();
    }

    @Override
    public boolean testCountryExists(String country) {
        return service.isValidCountry(country);
    }

}

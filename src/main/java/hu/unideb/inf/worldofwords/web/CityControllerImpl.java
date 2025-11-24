package hu.unideb.inf.worldofwords.web;

import hu.unideb.inf.worldofwords.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CityControllerImpl implements CityController{

    private final CityService service;

    @Override
    public ResponseEntity<List<String>> allCities() {
        return ResponseEntity.ok(service.allCities());
    }

    @Override
    public ResponseEntity<Boolean> checkCityExists(String name) {
        return ResponseEntity.ok(service.isValidCity(name));
    }

    @Override
    public ResponseEntity<List<String>> searchCities(String startingLetter, String containing) {
        return ResponseEntity.ok(service.searchCities(startingLetter, containing));
    }

}

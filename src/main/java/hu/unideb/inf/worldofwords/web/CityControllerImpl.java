package hu.unideb.inf.worldofwords.web;

import hu.unideb.inf.worldofwords.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CityControllerImpl implements CityController{

    private final CityService service;

    @Override
    public List<String> allCities() {
        return service.allCities();
    }

    @Override
    public boolean testCityExists(String city) {
        return service.isValidCity(city);
    }

}

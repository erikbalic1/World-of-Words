package hu.unideb.inf.worldofwords.service;

import hu.unideb.inf.worldofwords.model.City;
import hu.unideb.inf.worldofwords.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository repository;

    @Override
    public boolean isValidCity(String city) {
        return repository.existsCityByNameIgnoreCase(city);
    }

    @Override
    public List<String> allCities() {
        return repository.findAll().stream().map(City::getName).collect(Collectors.toList());
    }


}

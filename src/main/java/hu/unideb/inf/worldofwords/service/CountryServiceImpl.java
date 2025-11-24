package hu.unideb.inf.worldofwords.service;

import hu.unideb.inf.worldofwords.model.Country;
import hu.unideb.inf.worldofwords.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository repository;

    @Override
    public boolean isValidCountry(String country) {
        return repository.existsCountryByNameIgnoreCase(country);
    }

    @Override
    public List<String> allCountries() {
        return repository.findAll().stream().map(Country::getName).collect(Collectors.toList());
    }

    @Override
    public List<String> searchCountries(String startingLetter, String containing) {
        return repository.findAll()
                .stream()
                .map(Country::getName)
                .filter(name -> startingLetter == null || name.toLowerCase().startsWith(startingLetter.toLowerCase()))
                .filter(name -> containing == null || name.toLowerCase().contains(containing.toLowerCase()))
                .toList();
    }

}

package hu.unideb.inf.worldofwords.service;

import java.util.List;

public interface CountryService {

    boolean isValidCountry(String country);

    List<String> allCountries();

}

package hu.unideb.inf.worldofwords.service;

import java.util.List;

public interface CityService {

    boolean isValidCity(String city);

    List<String> allCities();

}

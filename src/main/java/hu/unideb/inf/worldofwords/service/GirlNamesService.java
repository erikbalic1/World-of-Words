package hu.unideb.inf.worldofwords.service;

import java.util.List;

public interface GirlNamesService {

    boolean isValidGirlName(String girlName);

    List<String> allGirlNames();

    List<String> searchGirlNames(String startingLetter, String containing);

}

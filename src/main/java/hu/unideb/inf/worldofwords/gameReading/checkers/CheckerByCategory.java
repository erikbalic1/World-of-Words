package hu.unideb.inf.worldofwords.gameReading.checkers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class CheckerByCategory {

    //===============================HARD-CODED===============================
    private static final String BASE_URL = "http://localhost:8082/api";
    private static final String COUNTRY_URL = "/countries/exists?name=";
    private static final String CITY_URL = "/cities/exists?name=";
    private static final String FEMALE_NAME_URL = "/girl-names/exists?name=";
    private static final String MALE_NAME_URL = "/boy-names/exists?name=";
    private static final String ANIMAL_URL = "/animals/exists?name=";
    //===============================HARD-CODED===============================


    private static final RestTemplate restTemplate = new RestTemplate();
    private static ResponseEntity<Boolean> response;

    public static boolean isValidCountry(String name) {
        response = restTemplate
                .getForEntity(BASE_URL + COUNTRY_URL + name, Boolean.class);
        return Boolean.TRUE.equals(response.getBody());
    }

    public static boolean isValidCity(String name) {
        response = restTemplate
                .getForEntity(BASE_URL + CITY_URL + name, Boolean.class);
        return Boolean.TRUE.equals(response.getBody());
    }

    public static boolean isValidFemaleName(String name) {
        response = restTemplate
                .getForEntity(BASE_URL + FEMALE_NAME_URL + name, Boolean.class);
        return Boolean.TRUE.equals(response.getBody());
    }

    public static boolean isValidMaleName(String name) {
        response = restTemplate
                .getForEntity(BASE_URL + MALE_NAME_URL + name, Boolean.class);
        return Boolean.TRUE.equals(response.getBody());
    }

    public static boolean isValidAnimalName(String name) {
        response = restTemplate
                .getForEntity(BASE_URL + ANIMAL_URL + name, Boolean.class);
        return Boolean.TRUE.equals(response.getBody());
    }

}

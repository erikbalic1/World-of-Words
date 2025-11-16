package hu.unideb.inf.worldofwords.service;

import java.util.List;

public interface AnimalService {

    boolean isValidAnimal(String animal);

    List<String> allAnimals();
}

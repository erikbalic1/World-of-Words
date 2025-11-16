package hu.unideb.inf.worldofwords.web;

import hu.unideb.inf.worldofwords.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AnimalControllerImpl implements AnimalController {

    private final AnimalService service;

    @Override
    public List<String> allAnimals() {
        return service.allAnimals();
    }

    @Override
    public boolean testAnimalExists(String animal) {
        return service.isValidAnimal(animal);
    }

}

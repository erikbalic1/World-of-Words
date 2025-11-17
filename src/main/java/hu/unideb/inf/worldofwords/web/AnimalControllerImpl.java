package hu.unideb.inf.worldofwords.web;

import hu.unideb.inf.worldofwords.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AnimalControllerImpl implements AnimalController {

    private final AnimalService service;

    @Override
    public ResponseEntity<List<String>> allAnimals() {
        return ResponseEntity.ok(service.allAnimals());
    }

    @Override
    public ResponseEntity<Boolean> checkAnimalExists(String name) {
        return ResponseEntity.ok(service.isValidAnimal(name));
    }

    @Override
    public ResponseEntity<List<String>> searchAnimals(String startingLetter, String containing) {
        return ResponseEntity.ok(service.searchAnimals(startingLetter, containing));
    }

}

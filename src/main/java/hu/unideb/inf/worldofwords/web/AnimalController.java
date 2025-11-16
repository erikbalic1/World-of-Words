package hu.unideb.inf.worldofwords.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface AnimalController {

    @GetMapping("/testAnimals")
    List<String> allAnimals();

    @GetMapping("/testAnimal")
    boolean testAnimalExists(@RequestParam String animal);

}

package hu.unideb.inf.worldofwords.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public interface AnimalController {

    @GetMapping("/animals")
    ResponseEntity<List<String>> allAnimals();

    @GetMapping("/animals/exists")
    ResponseEntity<Boolean> checkAnimalExists(@RequestParam String name);

    @GetMapping("/animals/search")
    ResponseEntity<List<String>> searchAnimals(
            @RequestParam(required = false) String startingLetter,
            @RequestParam(required = false) String containing
    );

}

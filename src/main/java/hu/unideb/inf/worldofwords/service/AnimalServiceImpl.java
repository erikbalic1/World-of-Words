package hu.unideb.inf.worldofwords.service;

import hu.unideb.inf.worldofwords.model.Animal;
import hu.unideb.inf.worldofwords.repository.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository repository;

    @Override
    public boolean isValidAnimal(String animal) {
        return repository.existsAnimalByNameIgnoreCase(animal);
    }

    @Override
    public List<String> allAnimals() {
        return repository.findAll().stream().map(Animal::getName).collect(Collectors.toList());
    }

    @Override
    public List<String> searchAnimals(String startingLetter, String containing) {
        return repository.findAll()
                .stream()
                .map(Animal::getName)
                .filter(name -> startingLetter == null || name.toLowerCase().startsWith(startingLetter.toLowerCase()))
                .filter(name -> containing == null || name.toLowerCase().contains(containing.toLowerCase()))
                .toList();
    }

}

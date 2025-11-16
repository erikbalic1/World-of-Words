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
        return repository.existsAnimalByName(animal);
    }

    @Override
    public List<String> allAnimals() {
        return repository.findAll().stream().map(Animal::getName).collect(Collectors.toList());
    }

}

package hu.unideb.inf.worldofwords.repository;

import hu.unideb.inf.worldofwords.model.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends MongoRepository<Animal, String> {
    boolean existsAnimalByName(String name);
}

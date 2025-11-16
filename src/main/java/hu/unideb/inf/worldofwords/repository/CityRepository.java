package hu.unideb.inf.worldofwords.repository;

import hu.unideb.inf.worldofwords.model.City;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends MongoRepository<City, String> {
    boolean existsCityByNameIgnoreCase(String name);
}

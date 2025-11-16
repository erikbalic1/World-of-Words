package hu.unideb.inf.worldofwords.repository;

import hu.unideb.inf.worldofwords.model.Country;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends MongoRepository<Country, String> {
    boolean existsCountryByName(String name);
}

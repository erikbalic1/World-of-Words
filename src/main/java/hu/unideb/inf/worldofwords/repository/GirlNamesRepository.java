package hu.unideb.inf.worldofwords.repository;

import hu.unideb.inf.worldofwords.model.GirlName;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GirlNamesRepository extends MongoRepository<GirlName, String> {
    boolean existsGirlNameByName(String name);
}

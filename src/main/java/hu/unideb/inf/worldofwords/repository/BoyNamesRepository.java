package hu.unideb.inf.worldofwords.repository;

import hu.unideb.inf.worldofwords.model.BoyName;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoyNamesRepository extends MongoRepository<BoyName, String> {
    boolean existsBoyNameByName(String name);
}

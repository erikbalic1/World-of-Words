package hu.unideb.inf.worldofwords.repository;

import hu.unideb.inf.worldofwords.model.LeaderboardEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaderboardRepository extends MongoRepository<LeaderboardEntry, String> {
    Optional<LeaderboardEntry> findByPlayerName(String playerName);
}

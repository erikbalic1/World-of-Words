package hu.unideb.inf.worldofwords.service;

import hu.unideb.inf.worldofwords.gameReading.core.GameCoordinator;
import hu.unideb.inf.worldofwords.model.GameResultDTO;
import hu.unideb.inf.worldofwords.model.LeaderboardEntry;
import hu.unideb.inf.worldofwords.repository.LeaderboardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameCoordinator gameCoordinator = new GameCoordinator();
    private final LeaderboardRepository leaderboardRepository;

    @Override
    public int submit(GameResultDTO result) {

        int score = gameCoordinator.getScore(
                result.getStartingLetter(),
                result.getCountryGiven(),
                result.getCountryTimeInMillis(),
                result.getCityGiven(),
                result.getCityTimeInMillis(),
                result.getBoyNameGiven(),
                result.getBoyTimeInMillis(),
                result.getGirlNameGiven(),
                result.getGirlTimeInMillis(),
                result.getAnimalGiven(),
                result.getAnimalTimeInMillis()
        );

        leaderboardRepository.save(
                LeaderboardEntry
                        .builder()
                        .playerName(result.getUsername())
                        .score(score)
                        .build()
        );

        return score;
    }
}

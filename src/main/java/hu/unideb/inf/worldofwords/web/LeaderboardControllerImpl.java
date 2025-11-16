package hu.unideb.inf.worldofwords.web;

import hu.unideb.inf.worldofwords.model.LeaderboardEntry;
import hu.unideb.inf.worldofwords.service.LeaderboardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class LeaderboardControllerImpl implements LeaderboardController{

    private final LeaderboardService service;

    @Override
    public List<LeaderboardEntry> testLeaderboard() {
        return service.getLeaderboard();
    }

    @Override
    public LeaderboardEntry updateLeaderboard(LeaderboardEntry entry) {
        return service.updateLeaderboard(entry);
    }

    @Override
    public Optional<LeaderboardEntry> testLeaderboard(String playerName) {
        return service.findByPlayerName(playerName);
    }

}

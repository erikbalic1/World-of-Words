package hu.unideb.inf.worldofwords.service;

import hu.unideb.inf.worldofwords.model.LeaderboardEntry;

import java.util.List;
import java.util.Optional;

public interface LeaderboardService {

    List<LeaderboardEntry> getLeaderboard();

    LeaderboardEntry updateLeaderboard(LeaderboardEntry entry);

    Optional<LeaderboardEntry> findByPlayerName(String playerName);

}

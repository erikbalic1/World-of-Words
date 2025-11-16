package hu.unideb.inf.worldofwords.service;

import hu.unideb.inf.worldofwords.model.LeaderboardEntry;
import hu.unideb.inf.worldofwords.repository.LeaderboardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LeaderboardServiceImpl implements LeaderboardService {

    private final LeaderboardRepository repository;

    @Override
    public List<LeaderboardEntry> getLeaderboard() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "score"));
    }

    @Override
    public LeaderboardEntry updateLeaderboard(LeaderboardEntry entry) {
        return repository.save(entry);
    }

    @Override
    public Optional<LeaderboardEntry> findByPlayerName(String playerName) {
        return repository.findByPlayerName(playerName);
    }
}

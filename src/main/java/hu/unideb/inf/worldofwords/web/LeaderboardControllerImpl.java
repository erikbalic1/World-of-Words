package hu.unideb.inf.worldofwords.web;

import hu.unideb.inf.worldofwords.model.LeaderboardEntry;
import hu.unideb.inf.worldofwords.service.LeaderboardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class LeaderboardControllerImpl implements LeaderboardController{

    private final LeaderboardService service;

    @Override
    public ResponseEntity<List<LeaderboardEntry>> getLeaderboard() {
        return ResponseEntity.ok(service.getLeaderboard());
    }

    @Override
    public ResponseEntity<LeaderboardEntry> updateLeaderboard(LeaderboardEntry entry) {
        return ResponseEntity.ok(service.updateLeaderboard(entry));
    }

    @Override
    public ResponseEntity<Optional<LeaderboardEntry>> getLeaderboardEntryByPlayerName(String name) {
        return ResponseEntity.ok(service.findByPlayerName(name));
    }

    @Override
    public ResponseEntity<Void> deleteLeaderboardEntry(String name) {
        service.deleteByPlayerName(name);
        return ResponseEntity.noContent().build();
    }


}

package hu.unideb.inf.worldofwords.web;

import hu.unideb.inf.worldofwords.model.LeaderboardEntry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/leaderboard")
public interface LeaderboardController {

    @GetMapping
    ResponseEntity<List<LeaderboardEntry>> getLeaderboard();

    @GetMapping("/player")
    ResponseEntity<Optional<LeaderboardEntry>> getLeaderboardEntryByPlayerName(@RequestParam String name);

    @PostMapping
    ResponseEntity<LeaderboardEntry> updateLeaderboard(@RequestBody LeaderboardEntry entry);

    @DeleteMapping("/player")
    ResponseEntity<Void> deleteLeaderboardEntry(@RequestParam String name);

}

package hu.unideb.inf.worldofwords.web;

import hu.unideb.inf.worldofwords.model.LeaderboardEntry;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public interface LeaderboardController {

    @GetMapping("/testLeaderboard")
    List<LeaderboardEntry> testLeaderboard();

    @GetMapping("/testLeaderboardEntry")
    Optional<LeaderboardEntry> testLeaderboard(@RequestParam String playerName);

    @PostMapping("/updateLeaderboard")
    LeaderboardEntry updateLeaderboard(@RequestBody LeaderboardEntry entry);

}

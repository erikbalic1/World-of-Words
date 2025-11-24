package hu.unideb.inf.worldofwords.web;

import hu.unideb.inf.worldofwords.model.GameResultDTO;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public interface GameController {

    /**
     * Used to submit the given answers for the game.
     *
     * @param givenResult the words given by the player
     */
    @PostMapping("/submit")
    ResponseEntity<Integer> submit(@RequestBody @NonNull GameResultDTO givenResult);

}

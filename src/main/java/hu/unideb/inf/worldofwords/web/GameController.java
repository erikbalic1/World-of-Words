package hu.unideb.inf.worldofwords.web;

import hu.unideb.inf.worldofwords.model.GameResultDTO;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
public interface GameController {

    /**
     * Used to get the random letter for the game.
     * @return a random letter
     */
    @GetMapping("/game/letter")
    Character letter();

    /**
     * Used to submit the given answers for the game.
     *
     * @param givenResult the words given by the player
     */
    @PostMapping("/game/submit")
    int submit(@RequestBody @NonNull GameResultDTO givenResult);

    /**
     * Used to get the current score of the player.
     * @return null if the player has not submitted any answers yet, otherwise the current score
     */
    @GetMapping("/game/score")
    Integer score();



}

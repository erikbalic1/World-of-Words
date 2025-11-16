package hu.unideb.inf.worldofwords.web;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GameControllerImpl implements GameController{

    /**
     * Used to get the random letter for the game.
     *
     * @return a random letter
     */
    @Override
    public Character letter() {
        return null;
    }

    /**
     * Used to submit the given answers for the game.
     *
     * @param given the words given by the player
     */
    @Override
    public void submit(@NonNull String given) {

    }

    /**
     * Used to get the current score of the player.
     *
     * @return null if the player has not submitted any answers yet, otherwise the current score
     */
    @Override
    public Integer score() {
        return 0;
    }

}

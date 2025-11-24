package hu.unideb.inf.worldofwords.web;

import hu.unideb.inf.worldofwords.model.GameResultDTO;
import hu.unideb.inf.worldofwords.service.GameService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GameControllerImpl implements GameController{

    private final GameService gameService;
    /**
     * Used to submit the given answers for the game.
     *
     * @param givenResult the words given by the player
     */
    @Override
    public ResponseEntity<Integer> submit(@NonNull GameResultDTO givenResult) {
        return ResponseEntity.ok(gameService.submit(givenResult));
    }

}

package hu.unideb.inf.worldofwords.web;

import hu.unideb.inf.worldofwords.service.GirlNamesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class GirlNameControllerImpl implements GirlNameController {

    private final GirlNamesService service;

    @Override
    public ResponseEntity<List<String>> allGirlNames() {
        return ResponseEntity.ok(service.allGirlNames());
    }

    @Override
    public ResponseEntity<Boolean> checkGirlNameExists(String name) {
        return ResponseEntity.ok(service.isValidGirlName(name));
    }

    @Override
    public ResponseEntity<List<String>> searchGirlNames(String startingLetter, String containing) {
        return ResponseEntity.ok(service.searchGirlNames(startingLetter, containing));
    }

}

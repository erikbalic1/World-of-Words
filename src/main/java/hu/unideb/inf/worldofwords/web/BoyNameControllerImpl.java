package hu.unideb.inf.worldofwords.web;

import hu.unideb.inf.worldofwords.service.BoyNamesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BoyNameControllerImpl implements BoyNameController{

    private final BoyNamesService service;

    @Override
    public ResponseEntity<List<String>> allBoyNames() {
        return ResponseEntity.ok(service.allBoyNames());
    }

    @Override
    public ResponseEntity<Boolean> checkBoyNameExists(String name) {
        return ResponseEntity.ok(service.isValidBoyName(name));
    }

    @Override
    public ResponseEntity<List<String>> searchBoyNames(String startingLetter, String containing) {
        return ResponseEntity.ok(service.searchBoyNames(startingLetter, containing));
    }

}

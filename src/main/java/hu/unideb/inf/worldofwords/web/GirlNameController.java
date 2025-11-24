package hu.unideb.inf.worldofwords.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/girl-names")
public interface GirlNameController {

    @GetMapping
    ResponseEntity<List<String>> allGirlNames();

    @GetMapping("/exists")
    ResponseEntity<Boolean> checkGirlNameExists(@RequestParam String name);

    @GetMapping("/search")
    ResponseEntity<List<String>> searchGirlNames(
            @RequestParam(required = false) String startingLetter,
            @RequestParam(required = false) String containing
    );

}

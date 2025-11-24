package hu.unideb.inf.worldofwords.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public interface BoyNameController {

    @GetMapping("/boy-names")
    ResponseEntity<List<String>> allBoyNames();

    @GetMapping("/boy-names/exists")
    ResponseEntity<Boolean> checkBoyNameExists(@RequestParam String name);

    @GetMapping("/boy-names/search")
    ResponseEntity<List<String>> searchBoyNames(
            @RequestParam(required = false) String startingLetter,
            @RequestParam(required = false) String containing
    );

}

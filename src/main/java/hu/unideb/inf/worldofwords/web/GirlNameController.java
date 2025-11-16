package hu.unideb.inf.worldofwords.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface GirlNameController {

    @GetMapping("/testGirlNames")
    List<String> allGirlNames();

    @GetMapping("/testGirlName")
    boolean testGirlNameExists(@RequestParam String girlName);

}

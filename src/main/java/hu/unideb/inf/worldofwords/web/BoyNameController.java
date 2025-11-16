package hu.unideb.inf.worldofwords.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface BoyNameController {

    @GetMapping("/testBoyNames")
    List<String> allBoyNames();

    @GetMapping("/testBoyName")
    boolean testBoyNameExists(@RequestParam String boyName);

}

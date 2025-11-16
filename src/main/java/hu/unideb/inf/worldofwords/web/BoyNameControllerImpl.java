package hu.unideb.inf.worldofwords.web;

import hu.unideb.inf.worldofwords.service.BoyNamesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BoyNameControllerImpl implements BoyNameController{

    private final BoyNamesService service;

    @Override
    public List<String> allBoyNames() {
        return service.allBoyNames();
    }

    @Override
    public boolean testBoyNameExists(String boyName) {
        return service.isValidBoyName(boyName);
    }

}

package hu.unideb.inf.worldofwords.web;

import hu.unideb.inf.worldofwords.service.GirlNamesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class GirlNameControllerImpl implements GirlNameController {

    private final GirlNamesService service;

    @Override
    public List<String> allGirlNames() {
        return service.allGirlNames();
    }

    @Override
    public boolean testGirlNameExists(String girlName) {
        return service.isValidGirlName(girlName);
    }

}

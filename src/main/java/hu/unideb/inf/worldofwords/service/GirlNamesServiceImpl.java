package hu.unideb.inf.worldofwords.service;

import hu.unideb.inf.worldofwords.model.GirlName;
import hu.unideb.inf.worldofwords.repository.GirlNamesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GirlNamesServiceImpl implements GirlNamesService {

    private final GirlNamesRepository repository;

    @Override
    public boolean isValidGirlName(String girlName) {
        return repository.existsGirlNameByName(girlName);
    }

    @Override
    public List<String> allGirlNames() {
        return repository.findAll().stream().map(GirlName::getName).collect(Collectors.toList());
    }

}

package hu.unideb.inf.worldofwords.service;

import hu.unideb.inf.worldofwords.model.BoyName;
import hu.unideb.inf.worldofwords.repository.BoyNamesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoyNamesServiceImpl implements BoyNamesService {

    private final BoyNamesRepository repository;

    @Override
    public boolean isValidBoyName(String boyName) {
        return repository.existsBoyNameByName(boyName);
    }

    @Override
    public List<String> allBoyNames() {
        return repository.findAll().stream().map(BoyName::getName).collect(Collectors.toList());
    }

}

package hu.unideb.inf.worldofwords.service;

import java.util.List;

public interface BoyNamesService {

    boolean isValidBoyName(String boyName);

    List<String> allBoyNames();
}

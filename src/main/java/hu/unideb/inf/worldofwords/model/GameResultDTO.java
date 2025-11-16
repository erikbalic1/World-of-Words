package hu.unideb.inf.worldofwords.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class GameResultDTO {

    private final char startingLetter;

    private final String countryGiven;
    private final long countryTimeInMillis;
    private final String cityGiven;
    private final long cityTimeInMillis;
    private final String boyNameGiven;
    private final long boyTimeInMillis;
    private final String girlNameGiven;
    private final long girlTimeInMillis;
    private final String animalGiven;
    private final long animalTimeInMillis;

}

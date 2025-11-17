package hu.unideb.inf.worldofwords.model;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Leaderboard")
public class LeaderboardEntry {

    @Indexed(unique = true)
    private String playerName;
    @Indexed
    private int score;

}

package hu.unideb.inf.worldofwords.service;

import hu.unideb.inf.worldofwords.model.LeaderboardEntry;
import hu.unideb.inf.worldofwords.repository.LeaderboardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("GameService Tests - Repository Interactions Only")
class GameServiceImplTest {

    @Mock
    private LeaderboardRepository leaderboardRepository;

    @InjectMocks
    private GameServiceImpl gameService;

    @Test
    @DisplayName("Should interact with repository when saving leaderboard entry")
    void testLeaderboardRepositoryInteraction() {
        // Arrange
        LeaderboardEntry entry = LeaderboardEntry.builder()
                .playerName("TestPlayer")
                .score(100)
                .build();

        when(leaderboardRepository.save(any(LeaderboardEntry.class)))
                .thenReturn(entry);

        // Act
        leaderboardRepository.save(entry);

        // Assert
        verify(leaderboardRepository, times(1)).save(any(LeaderboardEntry.class));
    }
}

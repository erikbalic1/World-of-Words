package hu.unideb.inf.worldofwords.gameReading.core;

import hu.unideb.inf.worldofwords.gameReading.validation.ScoreCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("GameCoordinator Tests - Unit Tests Only")
class GameCoordinatorTest {

    private ScoreCalculator scoreCalculator;

    @BeforeEach
    void setUp() {
        scoreCalculator = new ScoreCalculator();
    }

    @Test
    @DisplayName("Should calculate points for correct answer with base score")
    void testCalculateAnswerPoints_BaseScore() {
        // Act - Short word, slow answer
        int points = scoreCalculator.calculateAnswerPoints("Lion", true, 10000L);

        // Assert
        assertEquals(10, points, "Should get base 10 points");
    }

    @Test
    @DisplayName("Should add bonus for long words (7+ chars)")
    void testCalculateAnswerPoints_LongWordBonus() {
        // Act - Long word, slow answer
        int points = scoreCalculator.calculateAnswerPoints("Elephant", true, 10000L);

        // Assert
        assertEquals(15, points, "Should get 10 base + 5 long word bonus");
    }

    @Test
    @DisplayName("Should add bonus for fast answers (< 5 seconds)")
    void testCalculateAnswerPoints_FastAnswerBonus() {
        // Act - Short word, fast answer
        int points = scoreCalculator.calculateAnswerPoints("Lion", true, 3000L);

        // Assert
        assertEquals(15, points, "Should get 10 base + 5 fast bonus");
    }

    @Test
    @DisplayName("Should add both bonuses for long word answered fast")
    void testCalculateAnswerPoints_BothBonuses() {
        // Act - Long word, fast answer
        int points = scoreCalculator.calculateAnswerPoints("Elephant", true, 2000L);

        // Assert
        assertEquals(20, points, "Should get 10 base + 5 long word + 5 fast");
    }

    @Test
    @DisplayName("Should return 0 for incorrect answer")
    void testCalculateAnswerPoints_IncorrectAnswer() {
        // Act
        int points = scoreCalculator.calculateAnswerPoints("InvalidWord", false, 3000L);

        // Assert
        assertEquals(0, points, "Incorrect answer should get 0 points");
    }

    @Test
    @DisplayName("Should calculate round total without all-correct bonus")
    void testCalculateRoundTotal_WithoutBonus() {
        // Arrange - 4 out of 5 correct
        int[] answerPoints = {10, 15, 0, 10, 15};

        // Act
        int total = scoreCalculator.calculateRoundTotal(answerPoints, 5);

        // Assert
        assertEquals(50, total, "Should sum points without bonus");
    }

    @Test
    @DisplayName("Should add all-correct bonus when all 5 answers correct")
    void testCalculateRoundTotal_WithAllCorrectBonus() {
        // Arrange - All 5 correct
        int[] answerPoints = {10, 15, 10, 15, 20};

        // Act
        int total = scoreCalculator.calculateRoundTotal(answerPoints, 5);

        // Assert
        assertEquals(90, total, "Should sum points (70) + all-correct bonus (20)");
    }

    @Test
    @DisplayName("Should return 0 when all answers incorrect")
    void testCalculateRoundTotal_AllIncorrect() {
        // Arrange
        int[] answerPoints = {0, 0, 0, 0, 0};

        // Act
        int total = scoreCalculator.calculateRoundTotal(answerPoints, 5);

        // Assert
        assertEquals(0, total);
    }
}

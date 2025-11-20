package hu.unideb.inf.worldofwords.gameReading.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ScoreCalculator Tests")
class ScoreCalculatorTest {

    private ScoreCalculator scoreCalculator;

    @BeforeEach
    void setUp() {
        scoreCalculator = new ScoreCalculator();
    }

    @Test
    @DisplayName("Should return 0 points for incorrect answer")
    void testCalculateAnswerPoints_IncorrectAnswer() {
        // Act
        int points = scoreCalculator.calculateAnswerPoints("Lion", false, 3000L);

        // Assert
        assertEquals(0, points);
    }

    @Test
    @DisplayName("Should return base points for correct short answer with slow time")
    void testCalculateAnswerPoints_BasePoints() {
        // Act
        int points = scoreCalculator.calculateAnswerPoints("Lion", true, 10000L);

        // Assert
        assertEquals(10, points, "Should get base 10 points");
    }

    @Test
    @DisplayName("Should add long word bonus for 7+ character words")
    void testCalculateAnswerPoints_LongWordBonus() {
        // Act
        int points = scoreCalculator.calculateAnswerPoints("Elephant", true, 10000L);

        // Assert
        assertEquals(15, points, "Should get base 10 + 5 long word bonus");
    }

    @Test
    @DisplayName("Should add fast answer bonus for answers under 5 seconds")
    void testCalculateAnswerPoints_FastAnswerBonus() {
        // Act
        int points = scoreCalculator.calculateAnswerPoints("Lion", true, 4000L);

        // Assert
        assertEquals(15, points, "Should get base 10 + 5 fast answer bonus");
    }

    @Test
    @DisplayName("Should add both bonuses for long word answered fast")
    void testCalculateAnswerPoints_BothBonuses() {
        // Act
        int points = scoreCalculator.calculateAnswerPoints("Elephant", true, 3000L);

        // Assert
        assertEquals(20, points, "Should get base 10 + 5 long word + 5 fast answer");
    }

    @ParameterizedTest
    @CsvSource({
            "Cat, 3000, 15",      // Short word, fast answer
            "Elephant, 10000, 15", // Long word, slow answer
            "Elephant, 3000, 20",  // Long word, fast answer
            "Dog, 10000, 10"       // Short word, slow answer
    })
    @DisplayName("Should calculate correct points for various scenarios")
    void testCalculateAnswerPoints_ParameterizedScenarios(String word, long time, int expected) {
        // Act
        int points = scoreCalculator.calculateAnswerPoints(word, true, time);

        // Assert
        assertEquals(expected, points);
    }

    @Test
    @DisplayName("Should calculate round total without all-correct bonus")
    void testCalculateRoundTotal_WithoutBonus() {
        // Arrange
        int[] answerPoints = {10, 15, 0, 10, 15}; // 4 out of 5 correct

        // Act
        int total = scoreCalculator.calculateRoundTotal(answerPoints, 5);

        // Assert
        assertEquals(50, total, "Should sum all points without bonus");
    }

    @Test
    @DisplayName("Should add all-correct bonus when all answers are correct")
    void testCalculateRoundTotal_WithAllCorrectBonus() {
        // Arrange
        int[] answerPoints = {10, 15, 10, 15, 20}; // All 5 correct

        // Act
        int total = scoreCalculator.calculateRoundTotal(answerPoints, 5);

        // Assert
        assertEquals(90, total, "Should sum all points (70) plus all-correct bonus (20)");
    }

    @Test
    @DisplayName("Should return 0 when all answers are incorrect")
    void testCalculateRoundTotal_AllIncorrect() {
        // Arrange
        int[] answerPoints = {0, 0, 0, 0, 0};

        // Act
        int total = scoreCalculator.calculateRoundTotal(answerPoints, 5);

        // Assert
        assertEquals(0, total);
    }

    @Test
    @DisplayName("Should return correct max points per answer")
    void testGetMaxPointsPerAnswer() {
        // Act
        int maxPoints = scoreCalculator.getMaxPointsPerAnswer();

        // Assert
        assertEquals(20, maxPoints, "Max points should be 10 base + 5 long word + 5 fast");
    }

    @Test
    @DisplayName("Should return correct all-correct bonus value")
    void testGetAllCorrectBonus() {
        // Act
        int bonus = scoreCalculator.getAllCorrectBonus();

        // Assert
        assertEquals(20, bonus);
    }

    @Test
    @DisplayName("Should handle exactly 5000ms (threshold boundary)")
    void testCalculateAnswerPoints_AtThreshold() {
        // Act - exactly at 5000ms should NOT get fast bonus
        int points = scoreCalculator.calculateAnswerPoints("Lion", true, 5000L);

        // Assert
        assertEquals(10, points, "At 5000ms threshold should only get base points");
    }

    @Test
    @DisplayName("Should handle exactly 7 characters (long word threshold)")
    void testCalculateAnswerPoints_SevenCharacters() {
        // Act
        int points = scoreCalculator.calculateAnswerPoints("Giraffe", true, 10000L);

        // Assert
        assertEquals(15, points, "Exactly 7 characters should get long word bonus");
    }

    @Test
    @DisplayName("Should handle edge case with empty string")
    void testCalculateAnswerPoints_EmptyString() {
        // Act
        int points = scoreCalculator.calculateAnswerPoints("", true, 1000L);

        // Assert
        assertEquals(15, points, "Empty string should still get base + fast bonus if marked correct");
    }
}

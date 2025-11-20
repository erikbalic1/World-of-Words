package hu.unideb.inf.worldofwords.gameReading.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LetterGenerator Tests")
class LetterGeneratorTest {

    private LetterGenerator letterGenerator;

    @BeforeEach
    void setUp() {
        letterGenerator = new LetterGenerator();
    }

    @Test
    @DisplayName("Should generate uppercase letter between A and Z")
    void testGenerateRandomLetter_IsUppercase() {
        // Act
        char letter = letterGenerator.generateRandomLetter();

        // Assert
        assertTrue(letter >= 'A' && letter <= 'Z',
                "Generated letter should be uppercase between A and Z, got: " + letter);
    }

    @RepeatedTest(50)
    @DisplayName("Should consistently generate valid letters (repeated test)")
    void testGenerateRandomLetter_RepeatedValidation() {
        // Act
        char letter = letterGenerator.generateRandomLetter();

        // Assert
        assertTrue(letter >= 'A' && letter <= 'Z',
                "Generated letter should be uppercase between A and Z");
    }

    @Test
    @DisplayName("Should generate different letters over multiple calls")
    void testGenerateRandomLetter_ProducesVariety() {
        // Arrange
        Set<Character> generatedLetters = new HashSet<>();
        int iterations = 100;

        // Act
        for (int i = 0; i < iterations; i++) {
            generatedLetters.add(letterGenerator.generateRandomLetter());
        }

        // Assert
        assertTrue(generatedLetters.size() > 1,
                "Should generate more than one distinct letter over " + iterations + " iterations");
    }

    @Test
    @DisplayName("Should generate all 26 letters eventually")
    void testGenerateRandomLetter_CoverageOfAllLetters() {
        // Arrange
        Set<Character> generatedLetters = new HashSet<>();
        int maxAttempts = 10000;

        // Act
        for (int i = 0; i < maxAttempts && generatedLetters.size() < 26; i++) {
            generatedLetters.add(letterGenerator.generateRandomLetter());
        }

        // Assert
        assertTrue(generatedLetters.size() >= 20,
                "Should generate at least 20 different letters out of 26 in " + maxAttempts + " attempts");
    }

    @Test
    @DisplayName("Should not generate lowercase letters")
    void testGenerateRandomLetter_NoLowercase() {
        // Arrange
        int iterations = 100;

        // Act & Assert
        for (int i = 0; i < iterations; i++) {
            char letter = letterGenerator.generateRandomLetter();
            assertFalse(letter >= 'a' && letter <= 'z',
                    "Should not generate lowercase letters, got: " + letter);
        }
    }

    @Test
    @DisplayName("Should not generate special characters or digits")
    void testGenerateRandomLetter_NoSpecialCharacters() {
        // Arrange
        int iterations = 100;

        // Act & Assert
        for (int i = 0; i < iterations; i++) {
            char letter = letterGenerator.generateRandomLetter();
            assertTrue(Character.isLetter(letter),
                    "Should only generate letters, not special characters or digits, got: " + letter);
        }
    }
}

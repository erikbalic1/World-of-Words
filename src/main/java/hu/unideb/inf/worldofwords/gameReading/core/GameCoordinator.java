package hu.unideb.inf.worldofwords.gameReading.core;

import hu.unideb.inf.worldofwords.gameReading.io.InputOutputHandler;
import hu.unideb.inf.worldofwords.gameReading.validation.ScoreCalculator;
import hu.unideb.inf.worldofwords.gameReading.validation.ScoreValidator;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * GameCoordinator orchestrates the game flow and connects all components:
 * - InputOutputHandler for console I/O
 * - LetterGenerator for random letter generation
 * - ScoreValidator for word validation and scoring
 * - ScoreCalculator for point calculation
 */
public class GameCoordinator {
    private final InputOutputHandler ioHandler;
    private final LetterGenerator letterGenerator;
    private final ScoreValidator scoreValidator;
    private final ScoreCalculator scoreCalculator;
    
    private final String[] categories = new String[]{
        "Country", "City"/*, "Plant"*/, "Animal", "Boy name", "Girl name"
    };
    
    private int cumulativeScore = 0;
    private int roundsPlayed = 0;
    private String username = "";
    
    public GameCoordinator(InputOutputHandler ioHandler, 
                          LetterGenerator letterGenerator,
                          ScoreValidator scoreValidator) {
        this.ioHandler = ioHandler;
        this.letterGenerator = letterGenerator;
        this.scoreValidator = scoreValidator;
        this.scoreCalculator = new ScoreCalculator();
    }

    private int getScore(
            char startingLetter,
            String countryGiven,
            long countryTimeInMillis,
            String cityGiven,
            long cityTimeInMillis,
            String boyNameGiven,
            long boyTimeInMillis,
            String girlNameGiven,
            long girlTimeInMillis,
            String animalGiven,
            long animalTimeInMillis
    ) {
        int totalScore = 0;

        totalScore += scoreCalculator.calculateAnswerPoints(countryGiven,
                scoreValidator.validateWord(countryGiven, startingLetter, "Country"),
                countryTimeInMillis);
        totalScore += scoreCalculator.calculateAnswerPoints(cityGiven,
                scoreValidator.validateWord(cityGiven, startingLetter, "City"),
                cityTimeInMillis);
        totalScore += scoreCalculator.calculateAnswerPoints(boyNameGiven,
                scoreValidator.validateWord(boyNameGiven, startingLetter, "Boy name"),
                boyTimeInMillis);
        totalScore += scoreCalculator.calculateAnswerPoints(girlNameGiven,
                scoreValidator.validateWord(girlNameGiven, startingLetter, "Girl name"),
                girlTimeInMillis);
        totalScore += scoreCalculator.calculateAnswerPoints(animalGiven,
                scoreValidator.validateWord(animalGiven, startingLetter, "Animal"),
                animalTimeInMillis);

        return totalScore;

    }

    /**
     * Start the game: welcome, get username, play rounds, and save scores.
     */
    public void startGame() {
        ioHandler.displayWelcome();
        username = ioHandler.promptForUsername();
        
        boolean keepPlaying = true;
        while (keepPlaying) {
            playRound();
            keepPlaying = ioHandler.promptPlayAgain();
        }
        
        int totalPossible = roundsPlayed * categories.length;
        ioHandler.displayFarewell(username, roundsPlayed, cumulativeScore, totalPossible);
        ioHandler.displayGoodbye();
    }
    
    /**
     * Play one round: generate letter, collect timed input, validate, score, display.
     */
    private void playRound() {
        char letter = letterGenerator.generateRandomLetter();
        ioHandler.displayRandomLetter(letter);
        
        Map<String, String> answers = new LinkedHashMap<>();
        Map<String, Boolean> correctness = new LinkedHashMap<>();
        Map<String, Long> answerTimes = new LinkedHashMap<>();
        
        collectAnswersWithTiming(answers, letter, correctness, answerTimes);
        
        // Calculate points using ScoreCalculator
        int[] pointsPerAnswer = new int[categories.length];
        int totalScore = 0;
        
        for (int i = 0; i < categories.length; i++) {
            String category = categories[i];
            String answer = answers.get(category);
            boolean isCorrect = correctness.get(category);
            long timeInMillis = answerTimes.getOrDefault(category, 0L);
            
            int points = scoreCalculator.calculateAnswerPoints(answer, isCorrect, timeInMillis);
            pointsPerAnswer[i] = points;
        }
        
        totalScore = scoreCalculator.calculateRoundTotal(pointsPerAnswer, categories.length);
        
        ioHandler.displayRoundSummary(answers, correctness, categories);
        ioHandler.displayDetailedScore(pointsPerAnswer, totalScore, categories);
        
        cumulativeScore += totalScore;
        roundsPlayed += 1;
    }
    
    /**
     * Collect answers from the user, track timing, and validate them sequentially.
     */
    private void collectAnswersWithTiming(Map<String, String> answers, char letter, 
                                         Map<String, Boolean> correctness, Map<String, Long> answerTimes) {
        // Collect answers with timing
        for (String category : categories) {
            ioHandler.promptForCategory(category);
            long startTime = System.currentTimeMillis();
            String userAnswer = ioHandler.readLine();
            long endTime = System.currentTimeMillis();
            long timeTaken = endTime - startTime;
            
            answers.put(category, userAnswer);
            answerTimes.put(category, timeTaken);
        }
        
        // Validate all answers sequentially after collection
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Validating answers...");
        System.out.println("=".repeat(50));
        
        for (String category : categories) {
            String answer = answers.get(category);
            System.out.println("\n📝 Validating " + category + ": " + answer);
            boolean isValid = scoreValidator.validateWord(answer, letter, category);
            correctness.put(category, isValid);
        }
    }
}

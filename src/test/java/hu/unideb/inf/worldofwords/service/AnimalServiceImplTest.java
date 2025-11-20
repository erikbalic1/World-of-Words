package hu.unideb.inf.worldofwords.service;

import hu.unideb.inf.worldofwords.model.Animal;
import hu.unideb.inf.worldofwords.repository.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AnimalService Tests")
class AnimalServiceImplTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalServiceImpl animalService;

    private List<Animal> testAnimals;

    @BeforeEach
    void setUp() {
        testAnimals = Arrays.asList(
                new Animal("Lion"),
                new Animal("Tiger"),
                new Animal("Elephant"),
                new Animal("Leopard"),
                new Animal("Bear")
        );
    }

    @Test
    @DisplayName("Should return true when animal exists (case insensitive)")
    void testIsValidAnimal_WhenExists() {
        // Arrange
        when(animalRepository.existsAnimalByNameIgnoreCase("lion")).thenReturn(true);

        // Act
        boolean result = animalService.isValidAnimal("lion");

        // Assert
        assertTrue(result);
        verify(animalRepository, times(1)).existsAnimalByNameIgnoreCase("lion");
    }

    @Test
    @DisplayName("Should return false when animal does not exist")
    void testIsValidAnimal_WhenNotExists() {
        // Arrange
        when(animalRepository.existsAnimalByNameIgnoreCase("dragon")).thenReturn(false);

        // Act
        boolean result = animalService.isValidAnimal("dragon");

        // Assert
        assertFalse(result);
        verify(animalRepository, times(1)).existsAnimalByNameIgnoreCase("dragon");
    }

    @Test
    @DisplayName("Should return all animal names")
    void testAllAnimals() {
        // Arrange
        when(animalRepository.findAll()).thenReturn(testAnimals);

        // Act
        List<String> result = animalService.allAnimals();

        // Assert
        assertNotNull(result);
        assertEquals(5, result.size());
        assertTrue(result.contains("Lion"));
        assertTrue(result.contains("Tiger"));
        verify(animalRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return empty list when no animals exist")
    void testAllAnimals_WhenEmpty() {
        // Arrange
        when(animalRepository.findAll()).thenReturn(List.of());

        // Act
        List<String> result = animalService.allAnimals();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(animalRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should search animals by starting letter")
    void testSearchAnimals_ByStartingLetter() {
        // Arrange
        when(animalRepository.findAll()).thenReturn(testAnimals);

        // Act
        List<String> result = animalService.searchAnimals("L", null);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("Lion"));
        assertTrue(result.contains("Leopard"));
        assertFalse(result.contains("Tiger"));
    }

    @Test
    @DisplayName("Should search animals by containing string")
    void testSearchAnimals_ByContaining() {
        // Arrange
        when(animalRepository.findAll()).thenReturn(testAnimals);

        // Act
        List<String> result = animalService.searchAnimals(null, "ea");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains("Bear"));
    }

    @Test
    @DisplayName("Should search animals by starting letter and containing string")
    void testSearchAnimals_BothCriteria() {
        // Arrange
        when(animalRepository.findAll()).thenReturn(testAnimals);

        // Act
        List<String> result = animalService.searchAnimals("L", "eo");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains("Leopard"));
    }

    @Test
    @DisplayName("Should return all animals when both criteria are null")
    void testSearchAnimals_NoCriteria() {
        // Arrange
        when(animalRepository.findAll()).thenReturn(testAnimals);

        // Act
        List<String> result = animalService.searchAnimals(null, null);

        // Assert
        assertNotNull(result);
        assertEquals(5, result.size());
    }

    @Test
    @DisplayName("Should be case insensitive when searching")
    void testSearchAnimals_CaseInsensitive() {
        // Arrange
        when(animalRepository.findAll()).thenReturn(testAnimals);

        // Act
        List<String> result = animalService.searchAnimals("l", "ION");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains("Lion"));
    }
}

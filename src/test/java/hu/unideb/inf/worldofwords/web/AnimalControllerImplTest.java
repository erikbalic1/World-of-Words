package hu.unideb.inf.worldofwords.web;

import hu.unideb.inf.worldofwords.service.AnimalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AnimalControllerImpl.class)
@DisplayName("AnimalController Tests")
class AnimalControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnimalService animalService;

    private List<String> testAnimals;

    @BeforeEach
    void setUp() {
        testAnimals = Arrays.asList("Lion", "Tiger", "Elephant", "Bear");
    }

    @Test
    @DisplayName("GET /api/animals should return all animals")
    void testAllAnimals() throws Exception {
        // Arrange
        when(animalService.allAnimals()).thenReturn(testAnimals);

        // Act & Assert
        mockMvc.perform(get("/api/animals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0]").value("Lion"))
                .andExpect(jsonPath("$[1]").value("Tiger"))
                .andExpect(jsonPath("$[2]").value("Elephant"))
                .andExpect(jsonPath("$[3]").value("Bear"));

        verify(animalService, times(1)).allAnimals();
    }

    @Test
    @DisplayName("GET /api/animals/exists should return true when animal exists")
    void testCheckAnimalExists_True() throws Exception {
        // Arrange
        when(animalService.isValidAnimal("Lion")).thenReturn(true);

        // Act & Assert
        mockMvc.perform(get("/api/animals/exists")
                        .param("name", "Lion"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(animalService, times(1)).isValidAnimal("Lion");
    }

    @Test
    @DisplayName("GET /api/animals/exists should return false when animal does not exist")
    void testCheckAnimalExists_False() throws Exception {
        // Arrange
        when(animalService.isValidAnimal("Dragon")).thenReturn(false);

        // Act & Assert
        mockMvc.perform(get("/api/animals/exists")
                        .param("name", "Dragon"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));

        verify(animalService, times(1)).isValidAnimal("Dragon");
    }

    @Test
    @DisplayName("GET /api/animals/search should search by starting letter")
    void testSearchAnimals_ByStartingLetter() throws Exception {
        // Arrange
        List<String> filtered = Arrays.asList("Lion", "Leopard");
        when(animalService.searchAnimals("L", null)).thenReturn(filtered);

        // Act & Assert
        mockMvc.perform(get("/api/animals/search")
                        .param("startingLetter", "L"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0]").value("Lion"))
                .andExpect(jsonPath("$[1]").value("Leopard"));

        verify(animalService, times(1)).searchAnimals("L", null);
    }

    @Test
    @DisplayName("GET /api/animals/search should search by containing string")
    void testSearchAnimals_ByContaining() throws Exception {
        // Arrange
        List<String> filtered = Arrays.asList("Bear");
        when(animalService.searchAnimals(null, "ear")).thenReturn(filtered);

        // Act & Assert
        mockMvc.perform(get("/api/animals/search")
                        .param("containing", "ear"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0]").value("Bear"));

        verify(animalService, times(1)).searchAnimals(null, "ear");
    }

    @Test
    @DisplayName("GET /api/animals/search should search with both parameters")
    void testSearchAnimals_BothParameters() throws Exception {
        // Arrange
        List<String> filtered = Arrays.asList("Lion");
        when(animalService.searchAnimals("L", "io")).thenReturn(filtered);

        // Act & Assert
        mockMvc.perform(get("/api/animals/search")
                        .param("startingLetter", "L")
                        .param("containing", "io"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0]").value("Lion"));

        verify(animalService, times(1)).searchAnimals("L", "io");
    }

    @Test
    @DisplayName("GET /api/animals/search should return all when no parameters")
    void testSearchAnimals_NoParameters() throws Exception {
        // Arrange
        when(animalService.searchAnimals(null, null)).thenReturn(testAnimals);

        // Act & Assert
        mockMvc.perform(get("/api/animals/search"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4));

        verify(animalService, times(1)).searchAnimals(null, null);
    }
}

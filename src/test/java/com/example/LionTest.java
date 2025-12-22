package com.example;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LionTest {

    @ParameterizedTest
    @CsvSource({
            "Самец, true",
            "Самка, false"
    })
    void testDoesHaveMane(String sex, boolean expectedHasMane) throws Exception {
        FelinePredator felinePredator = mock(FelinePredator.class);
        Lion lion = new Lion(sex, felinePredator);
        assertEquals(expectedHasMane, lion.doesHaveMane());
    }

    @Test
    void testGetKittens() throws Exception {
        FelinePredator felinePredator = mock(FelinePredator.class);
        when(felinePredator.getKittens()).thenReturn(3);

        Lion lion = new Lion("Самец", felinePredator);
        int kittens = lion.getKittens();

        assertEquals(3, kittens);
        verify(felinePredator, times(1)).getKittens();
    }

    @Test
    void testGetFood() throws Exception {
        FelinePredator felinePredator = mock(FelinePredator.class);
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");
        when(felinePredator.eatMeat()).thenReturn(expectedFood);

        Lion lion = new Lion("Самец", felinePredator);
        List<String> actualFood = lion.getFood();

        assertEquals(expectedFood, actualFood);
        verify(felinePredator, times(1)).eatMeat();
    }

    @Test
    void testInvalidSex() {
        FelinePredator felinePredator = mock(FelinePredator.class);
        Exception exception = assertThrows(Exception.class,
                () -> new Lion("Некорректный пол", felinePredator));

        assertTrue(exception.getMessage().contains("Используйте допустимые значения пола животного"));
    }

    @Test
    void testGetFoodWithException() throws Exception {
        FelinePredator felinePredator = mock(FelinePredator.class);
        when(felinePredator.eatMeat()).thenThrow(new Exception("Исключение"));

        Lion lion = new Lion("Самец", felinePredator);
        Exception exception = assertThrows(Exception.class, lion::getFood);

        assertEquals("Исключение", exception.getMessage());
    }
    @Test
    void testGetFoodForFemale() throws Exception {
        FelinePredator felinePredator = mock(FelinePredator.class);
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");
        when(felinePredator.eatMeat()).thenReturn(expectedFood);

        Lion lion = new Lion("Самка", felinePredator);
        List<String> actualFood = lion.getFood();

        assertEquals(expectedFood, actualFood);
        verify(felinePredator, times(1)).eatMeat();
    }
}
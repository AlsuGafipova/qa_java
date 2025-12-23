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
        Predator predator = mock(Predator.class);
        Lion lion = new Lion(sex, predator);
        assertEquals(expectedHasMane, lion.doesHaveMane());
    }

    @Test
    void testGetKittens() throws Exception {
        Predator predator = mock(Predator.class);
        when(predator.getKittens()).thenReturn(3);

        Lion lion = new Lion("Самец", predator);
        int kittens = lion.getKittens();

        assertEquals(3, kittens);
        verify(predator, times(1)).getKittens();
    }

    @Test
    void testGetFood() throws Exception {
        Predator predator = mock(Predator.class);
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");
        when(predator.eatMeat()).thenReturn(expectedFood);

        Lion lion = new Lion("Самец", predator);
        List<String> actualFood = lion.getFood();

        assertEquals(expectedFood, actualFood);
        verify(predator, times(1)).eatMeat();
    }

    @Test
    void testInvalidSex() {
        Predator predator = mock(Predator.class);
        Exception exception = assertThrows(Exception.class,
                () -> new Lion("Некорректный пол", predator));

        assertTrue(exception.getMessage().contains("Используйте допустимые значения пола животного"));
    }

    @Test
    void testGetFoodWithException() throws Exception {
        Predator predator = mock(Predator.class);
        when(predator.eatMeat()).thenThrow(new Exception("Исключение"));

        Lion lion = new Lion("Самец", predator);
        Exception exception = assertThrows(Exception.class, lion::getFood);

        assertEquals("Исключение", exception.getMessage());
    }
    @Test
    void testGetFoodForFemale() throws Exception {
        Predator predator = mock(Predator.class);
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");
        when(predator.eatMeat()).thenReturn(expectedFood);

        Lion lion = new Lion("Самка", predator);
        List<String> actualFood = lion.getFood();

        assertEquals(expectedFood, actualFood);
        verify(predator, times(1)).eatMeat();
    }
}
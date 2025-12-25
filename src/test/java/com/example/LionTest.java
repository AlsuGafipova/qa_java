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
        Feline feline = mock(Feline.class);
        Lion lion = new Lion(sex, feline);
        assertEquals(expectedHasMane, lion.doesHaveMane());
    }

    @Test
    void testGetKittens() throws Exception {
        Feline feline = mock(Feline.class);
        when(feline.getKittens()).thenReturn(3);

        Lion lion = new Lion("Самец", feline);
        int kittens = lion.getKittens();

        assertEquals(3, kittens);
        verify(feline, times(1)).getKittens();
    }

    @Test
    void testGetFood() throws Exception {
        Feline feline = mock(Feline.class);
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");
        when(feline.getFood("Хищник")).thenReturn(expectedFood);

        Lion lion = new Lion("Самец", feline);
        List<String> actualFood = lion.getFood();

        assertEquals(expectedFood, actualFood);
        verify(feline, times(1)).getFood("Хищник");
    }

    @Test
    void testInvalidSex() {
        Feline feline = mock(Feline.class);
        Exception exception = assertThrows(Exception.class,
                () -> new Lion("Некорректный пол", feline));

        assertTrue(exception.getMessage().contains("Используйте допустимые значения пола животного"));
    }

    @Test
    void testGetFoodWithException() throws Exception {
        Feline feline = mock(Feline.class);
        when(feline.getFood("Хищник")).thenThrow(new Exception("Исключение"));

        Lion lion = new Lion("Самец", feline);
        Exception exception = assertThrows(Exception.class, lion::getFood);

        assertEquals("Исключение", exception.getMessage());
    }
    @Test
    void testGetKittensWithException() throws Exception {
        Feline feline = mock(Feline.class);
        when(feline.getKittens()).thenThrow(new RuntimeException("Ошибка котят"));

        Lion lion = new Lion("Самец", feline);
        Exception exception = assertThrows(RuntimeException.class, lion::getKittens);

        assertEquals("Ошибка котят", exception.getMessage());
    }
}
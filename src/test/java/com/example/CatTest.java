package com.example;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CatTest {

    private Feline felineMock;
    private Cat cat;

    @BeforeEach
    void setUp() {
        felineMock = mock(Feline.class);
        cat = new Cat(felineMock);
    }

    @Test
    void testGetSound() {
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    void testGetFood() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(felineMock.eatMeat()).thenReturn(expectedFood);

        List<String> actualFood = cat.getFood();

        assertEquals(expectedFood, actualFood);
        verify(felineMock, times(1)).eatMeat();
    }

    @Test
    void testGetFoodThrowsException() throws Exception {
        when(felineMock.eatMeat()).thenThrow(new Exception("Тестовое исключение"));

        Exception exception = assertThrows(Exception.class, () -> cat.getFood());
        assertEquals("Тестовое исключение", exception.getMessage());
        verify(felineMock, times(1)).eatMeat();
    }
    @Test
    void testConstructor() {
        Feline feline = mock(Feline.class);
        Cat cat = new Cat(feline);
        assertNotNull(cat);
    }
}
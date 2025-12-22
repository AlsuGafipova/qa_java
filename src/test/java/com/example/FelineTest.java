package com.example;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class FelineTest {

    @Test
    void testEatMeat() throws Exception {
        Feline feline = new Feline();
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");
        assertEquals(expectedFood, feline.eatMeat());
    }

    @Test
    void testGetFamily() {
        Feline feline = new Feline();
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    void testGetKittensWithoutParameters() {
        Feline feline = new Feline();
        assertEquals(1, feline.getKittens());
    }

    @ParameterizedTest
    @MethodSource("kittensDataProvider")
    void testGetKittensWithParameter(int kittensCount, int expected) {
        Feline feline = new Feline();
        assertEquals(expected, feline.getKittens(kittensCount));
    }

    static Stream<Object[]> kittensDataProvider() {
        return Stream.of(
                new Object[]{1, 1},
                new Object[]{2, 2},
                new Object[]{5, 5},
                new Object[]{0, 0}
        );
    }
    @Test
    void testAnimalGetFoodForHerbivore() throws Exception {
        Feline feline = new Feline();
        // Feline наследуется от Animal, можно проверить метод getFood
        // Но в Feline он переопределен через eatMeat
    }
}
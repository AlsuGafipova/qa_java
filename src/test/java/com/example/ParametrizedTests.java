package com.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ParametrizedTests {

    @ParameterizedTest
    @CsvSource({
            "Самец, true",
            "Самка, false"
    })
    void testLionManeParameterized(String sex, boolean expectedHasMane) throws Exception {
        FelinePredator felinePredator = mock(FelinePredator.class);
        Lion lion = new Lion(sex, felinePredator);
        assertEquals(expectedHasMane, lion.doesHaveMane());
    }
}
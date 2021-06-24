package com.lcdlv.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.lcdlv.app.Fraction.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FractionTest {

    @ParameterizedTest(name = "{0} simplifies => {1}")
    @MethodSource("simplifyFractions")
    void shouldSimplifyFractions(Fraction fraction, Fraction expectedResult) {
        assertThat(fraction.simplify()).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> simplifyFractions() {
        return Stream.of(
                Arguments.of(of(1, 2), of(1, 2)),
                Arguments.of(of(2, 2), of(1)),
                Arguments.of(of(4, 4), of(1)),
                Arguments.of(of(8, 4), of(2)),
                Arguments.of(of(100, 25), of(4)),
                Arguments.of(of(0, 25), of(0)),
                Arguments.of(of(-47, 25), of(-47, 25)),
                Arguments.of(of(1, -2), of(-1, 2))
        );
    }

    @Test
    void shouldThrowAnExceptionWhenDenominatorIsZero() {
        assertThrows(IllegalArgumentException.class, () -> Fraction.of(1, 0));
    }

}

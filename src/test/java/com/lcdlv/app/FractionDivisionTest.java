package com.lcdlv.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.lcdlv.app.Fraction.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FractionDivisionTest {

    @ParameterizedTest(name = "{0} divided by {1} => {2}")
    @MethodSource({
            "oneOfTheFractionIsOne",
            "denominatorIsOne",
            "negativeFractions",
            "fractionsWithDenominator"
    })
    void shouldDivideFractions(Fraction fraction, Fraction fractionToDivide, Fraction expectedResult) {
        assertThat(fraction.divide(fractionToDivide)).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> oneOfTheFractionIsOne() {
        return Stream.of(
                Arguments.of(of(1, 2), of(1), of(1, 2)),
                Arguments.of(of(1), of(1), of(1)),
                Arguments.of(of(5, 2), of(1), of(5, 2)),
                Arguments.of(of(1), of(4, 7), of(7, 4))
        );
    }


    private static Stream<Arguments> denominatorIsOne() {
        return Stream.of(
                Arguments.of(of(8), of(2), of(4)),
                Arguments.of(of(10), of(3), of(10, 3)),
                Arguments.of(of(21), of(4), of(21, 4))
        );
    }

    private static Stream<Arguments> negativeFractions() {
        return Stream.of(
                Arguments.of(of(-51), of(5), of(-51, 5)),
                Arguments.of(of(5), of(-4), of(-5, 4))
        );
    }

    private static Stream<Arguments> fractionsWithDenominator() {
        return Stream.of(
                Arguments.of(of(1, 3), of(1, 5), of(5, 3)),
                Arguments.of(of(4, 3), of(7, 8), of(32, 21))
        );
    }

    @Test
    void shouldThrowAnExceptionWhenDividingByZero() {
        assertThrows(IllegalArgumentException.class, () -> of(4).divide(of(0)));
    }

}

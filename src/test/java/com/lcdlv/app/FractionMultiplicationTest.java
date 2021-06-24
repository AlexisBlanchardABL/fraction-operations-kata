package com.lcdlv.app;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.lcdlv.app.Fraction.of;
import static org.assertj.core.api.Assertions.assertThat;

class FractionMultiplicationTest {

    @ParameterizedTest(name = "{0} multiplied by {1} => {2}")
    @MethodSource({
            "identity",
            "denominatorIsOne",
            "negativeFractions",
            "fractionsWithDenominator"
    })
    void shouldMultiplyFractions(Fraction fraction, Fraction fractionToMultiply, Fraction expectedResult) {
        assertThat(fraction.multiply(fractionToMultiply)).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> identity() {
        return Stream.of(
                Arguments.of(of(1, 2), of(1), of(1, 2)),
                Arguments.of(of(1), of(1), of(1)),
                Arguments.of(of(5, 2), of(1), of(5, 2)),
                Arguments.of(of(1), of(4, 7), of(4, 7))
        );
    }


    private static Stream<Arguments> denominatorIsOne() {
        return Stream.of(
                Arguments.of(of(8), of(2), of(16)),
                Arguments.of(of(10), of(3), of(30)),
                Arguments.of(of(21), of(4), of(84))
        );
    }

    private static Stream<Arguments> negativeFractions() {
        return Stream.of(
                Arguments.of(of(-51), of(5), of(-255)),
                Arguments.of(of(5), of(-4), of(-20))
        );
    }

    private static Stream<Arguments> fractionsWithDenominator() {
        return Stream.of(
                Arguments.of(of(1, 3), of(1, 5), of(1, 15)),
                Arguments.of(of(4, 3), of(7, 8), of(7, 6))
        );
    }

}

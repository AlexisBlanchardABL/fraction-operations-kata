package com.lcdlv.app;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.lcdlv.app.Fraction.of;
import static org.assertj.core.api.Assertions.assertThat;

class FractionSubtractionTest {

    @ParameterizedTest(name = "{0} - {1} = {2}")
    @MethodSource("subtractFractions")
    void shouldSubtractFractions(Fraction fraction, Fraction fractionToSubtract, Fraction expectedResult) {
        assertThat(fraction.subtract(fractionToSubtract)).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> subtractFractions() {
        return Stream.of(
                Arguments.of(of(1), of(1), of(0)),
                Arguments.of(of(3), of(8), of(-5)),
                Arguments.of(of(4), of(16), of(-12)),
                Arguments.of(of(3, 2), of(1, 2), of(1)),
                Arguments.of(of(2), of(5, 6), of(7, 6)),
                Arguments.of(of(2, 3), of(1, 4), of(5, 12))
        );
    }

}

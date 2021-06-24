package com.lcdlv.app;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.lcdlv.app.Fraction.of;
import static org.assertj.core.api.Assertions.assertThat;

class FractionMultiplicationTest {

    @ParameterizedTest(name = "{0} multiplied by {1} => {2}")
    @MethodSource("multiplyFractionsWhenIdentity")
    void shouldMultiplyFractions(Fraction fraction, Fraction fractionToMultiply, Fraction expectedResult) {
        assertThat(fraction.multiply(fractionToMultiply)).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> multiplyFractionsWhenIdentity() {
        return Stream.of(
                Arguments.of(of(1, 2), of(1), of(1, 2)),
                Arguments.of(of(1), of(1), of(1)),
                Arguments.of(of(5, 2), of(1), of(5, 2)),
                Arguments.of(of(1), of(4, 7), of(4, 7))
        );
    }

}

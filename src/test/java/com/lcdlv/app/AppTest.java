package com.lcdlv.app;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.lcdlv.app.Fraction.of;
import static org.assertj.core.api.Assertions.assertThat;


class AppTest {

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @MethodSource("addFractionSameDenominator")
    void shouldAddFractionsWithSameDenominator(Fraction fraction, Fraction fractionToAdd, Fraction expectedResult) {
        assertThat(fraction.add(fractionToAdd)).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> addFractionSameDenominator() {
        return Stream.of(
                Arguments.of(of(1, 1), of(1, 1), of(2, 1)),
                Arguments.of(of(3, 1), of(8, 1), of(11, 1)),
                Arguments.of(of(4, 1), of(16, 1), of(20, 1))
        );
    }

}

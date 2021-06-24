package com.lcdlv.app;

import org.junit.jupiter.api.Test;

import static com.lcdlv.app.Fraction.of;
import static org.assertj.core.api.Assertions.assertThat;

class FractionMultiplicationTest {

    @Test
    void shouldMultiplyFractions_1() {
        assertThat(of(1, 1).multiply(of(1, 1))).isEqualTo(of(1));
    }

    @Test
    void shouldMultiplyFractions_2() {
        assertThat(of(1, 2).multiply(of(1))).isEqualTo(of(1, 2));
    }

}

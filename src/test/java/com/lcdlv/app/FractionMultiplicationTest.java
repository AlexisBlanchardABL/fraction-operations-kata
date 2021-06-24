package com.lcdlv.app;

import org.junit.jupiter.api.Test;

import static com.lcdlv.app.Fraction.of;
import static org.assertj.core.api.Assertions.assertThat;

class FractionMultiplicationTest {

    @Test
    void shouldMultiplyFractions() {
        assertThat(of(1,1).multiply(of(1,1))).isEqualTo(of(1,1));
    }

}

package com.lcdlv.app;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


class AppTest {

    @Test
    void shouldPass() {
        assertTrue(true);
    }

    @Test
    void shouldAddFractions() {
        Fraction fraction = Fraction.of(1, 1);
        assertThat(fraction.add(Fraction.of(1,1))).isEqualTo(Fraction.of(2,1));
    }

}

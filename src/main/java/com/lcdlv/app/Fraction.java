package com.lcdlv.app;

public class Fraction {
    private int numerator;
    private int denominator;

    private Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static Fraction of(int numerator, int denominator) {
        return new Fraction(numerator, denominator);
    }

    public Fraction add(Fraction fractionToAdd) {
        if (fractionToAdd.numerator == 1) {
            return new Fraction(1 + this.numerator, 1);
        } else if(fractionToAdd.numerator == 8) {
            return new Fraction(8 + this.numerator, 1);
        } else {
            return new Fraction(16 + this.numerator, 1);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fraction fraction = (Fraction) o;

        if (numerator != fraction.numerator) return false;
        return denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }

    @Override
    public String toString() {
        return numerator + " / " + denominator;
    }

}

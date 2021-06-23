package com.lcdlv.app;

import java.math.BigInteger;

public class Fraction {
    private int numerator;
    private int denominator;

    private Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static Fraction of(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator value 0, is not legal");
        }
        return new Fraction(numerator, denominator).simplify();
    }

    public static Fraction of(int numerator) {
        return new Fraction(numerator, 1).simplify();
    }

    public Fraction add(Fraction fractionToAdd) {
        return Fraction.of((this.numerator * fractionToAdd.denominator) + (fractionToAdd.numerator * this.denominator), fractionToAdd.denominator * this.denominator);
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

    Fraction simplify() {
        int gcd = getGcd(this);
        return new Fraction(this.numerator / gcd, this.denominator / gcd);
    }

    private int getGcd(Fraction fraction) {
        return BigInteger.valueOf(fraction.numerator).gcd(BigInteger.valueOf(fraction.denominator)).intValue();
    }

}

package com.lcdlv.app;

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

    public Fraction add(Fraction fraction) {
        return Fraction.of((this.numerator * fraction.denominator) + (fraction.numerator * this.denominator), fraction.denominator * this.denominator);
    }

    public Fraction multiply(Fraction fraction) {
        return of(this.numerator * fraction.numerator, this.denominator * fraction.denominator);
    }

    public Fraction divide(Fraction fraction) {
        return multiply(fraction.reverse());
    }

    private Fraction reverse() {
        return of(this.denominator, this.numerator);
    }

    public Fraction subtract(Fraction fraction) {
        return add(fraction.opposite());
    }

    private Fraction opposite() {
        return of(-this.numerator, this.denominator);
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
        return numerator + (denominator != 1 ? " / " + denominator : "");
    }

    Fraction simplify() {
        if (this.denominator < 0) {
            this.denominator *= -1;
            this.numerator *= -1;
        }
        int gcd = GreaterCommonDivisor.compute(this.numerator, this.denominator);
        return new Fraction(this.numerator / gcd, this.denominator / gcd);
    }

}

package com.lcdlv.app;

import java.math.BigInteger;
import java.util.Objects;


public class Fraction {

    private BigInteger numerator;
    private BigInteger denominator;

    private Fraction(BigInteger numerator, BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static Fraction of(int numerator, int denominator) {
        return of(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }

    public static Fraction of(BigInteger numerator, BigInteger denominator) {
        if (denominator.intValue() == 0) {
            throw new IllegalArgumentException("Denominator value 0, is not legal");
        }
        return new Fraction(numerator, denominator).simplify();
    }

    public static Fraction of(int numerator) {
        return new Fraction(BigInteger.valueOf(numerator), BigInteger.valueOf(1)).simplify();
    }

    /**
     * a   c   ad + cb
     * _ + _ = _______
     * b   d     bd
     */
    public Fraction add(Fraction fraction) {
        BigInteger ad = this.numerator.multiply(fraction.denominator);
        BigInteger cb = fraction.numerator.multiply(this.denominator);
        BigInteger bd = this.denominator.multiply(fraction.denominator);
        return Fraction.of(ad.add(cb), bd);
    }

    /**
     * a   c    a * c
     * _ * _ = _______
     * b   d    b * d
     */
    public Fraction multiply(Fraction fraction) {
        BigInteger ac = this.numerator.multiply(fraction.numerator);
        BigInteger bd = this.denominator.multiply(fraction.denominator);
        return of(ac, bd);
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
        BigInteger numeratorOpposite = this.numerator.multiply(BigInteger.valueOf(-1));
        return of(numeratorOpposite, this.denominator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fraction fraction = (Fraction) o;

        if (!Objects.equals(this.numerator, fraction.numerator)) return false;
        return Objects.equals(this.denominator, fraction.denominator);
    }

    @Override
    public int hashCode() {
        int result = this.numerator != null ? this.numerator.hashCode() : 0;
        result = 31 * result + (this.denominator != null ? this.denominator.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.numerator.intValue() + (this.denominator.intValue() != 1 ? " / " + this.denominator.intValue() : "");
    }

    Fraction simplify() {
        if (this.denominator.signum() < 0) {
            this.denominator = this.denominator.multiply(BigInteger.valueOf(-1));
            this.numerator = this.numerator.multiply(BigInteger.valueOf(-1));
        }
        BigInteger gcd = this.numerator.gcd(this.denominator);
        return new Fraction(this.numerator.divide(gcd), this.denominator.divide(gcd));
    }

}

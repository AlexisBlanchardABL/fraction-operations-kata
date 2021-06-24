package com.lcdlv.app;


import static java.math.BigInteger.valueOf;

public class GreaterCommonDivisor {

    static int compute(int x, int y) {
        return valueOf(x).gcd(valueOf(y)).intValue();
    }

}

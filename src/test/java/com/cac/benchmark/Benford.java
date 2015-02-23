package com.cac.benchmark;

import java.math.BigDecimal;

/**
 * Created by shreyash on 16/2/15.
 */
public class Benford {

    public static double getBenfordProbability(int position, int digit, int precision) {
        if (position == 1 & digit == 0) {
            return Double.NaN;
        } else if (position == 1 & digit != 0) {
            return setPrecision((Math.log10((digit + 1.0) / digit)), precision);
        } else {
            double limInfer = Math.pow(10, (position - 2));
            double limSuper = Math.pow(10, (position - 1)) - 1;

            double Pd = 0d;
            for (double i = limInfer; i <= limSuper; i++) {
                Pd += Math.log10(1.0 + (1.0 / ((10.0 * i) + digit)));
            }
            return setPrecision(Pd, precision);
        }
    }

    private static double setPrecision(double value, int precision) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(precision, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
}

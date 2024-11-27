package utils;


import constant.NumericalConstants;

public class Truncator {
    private Truncator(){}

    public static double truncate(double num){
        double power = Math.pow(10, NumericalConstants.MAX_DECIMAL_PLACES);
        return Math.floor(num * power) / power;
    }
}

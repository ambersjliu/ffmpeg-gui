package utils;

import constant.NumericalConstants;

/**
 * Truncate second.
 */

public final class Truncator {
    private Truncator() {

    }

    /**
     * Truncate second down to two decimal places.
     * @param num seconds
     * @return truncate seconds
     */
    public static double truncate(double num) {
        final double power = Math.pow(10, NumericalConstants.MAX_DECIMAL_PLACES);
        return Math.floor(num * power) / power;
    }
}

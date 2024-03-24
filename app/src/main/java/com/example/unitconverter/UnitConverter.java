package com.example.unitconverter;



public class UnitConverter {

    // Length Conversions
    public static double convertLength(double value, String sourceUnit, String destinationUnit) {
        switch (sourceUnit) {
            case "inch":
                return convertFromInch(value, destinationUnit);
            case "foot":
                return convertFromFoot(value, destinationUnit);
            case "yard":
                return convertFromYard(value, destinationUnit);
            case "mile":
                return convertFromMile(value, destinationUnit);
            default:
                return value; // If source unit is unknown, return input value
        }
    }

    private static double convertFromInch(double value, String destinationUnit) {
        if (destinationUnit.equals("cm")) {
            return value * 2.54;
        }
        return value; // If destination unit is unknown, return input value
    }

    private static double convertFromFoot(double value, String destinationUnit) {
        if (destinationUnit.equals("cm")) {
            return value * 30.48;
        }
        return value; // If destination unit is unknown, return input value
    }

    private static double convertFromYard(double value, String destinationUnit) {
        if (destinationUnit.equals("cm")) {
            return value * 91.44;
        }
        return value; // If destination unit is unknown, return input value
    }

    private static double convertFromMile(double value, String destinationUnit) {
        if (destinationUnit.equals("km")) {
            return value * 1.60934;
        }
        return value; // If destination unit is unknown, return input value
    }

    // Weight Conversions
    public static double convertWeight(double value, String sourceUnit, String destinationUnit) {
        switch (sourceUnit) {
            case "pound":
                return convertFromPound(value, destinationUnit);
            case "ounce":
                return convertFromOunce(value, destinationUnit);
            case "ton":
                return convertFromTon(value, destinationUnit);
            default:
                return value; // If source unit is unknown, return input value
        }
    }

    private static double convertFromPound(double value, String destinationUnit) {
        if (destinationUnit.equals("kg")) {
            return value * 0.453592;
        }
        return value; // If destination unit is unknown, return input value
    }

    private static double convertFromOunce(double value, String destinationUnit) {
        if (destinationUnit.equals("g")) {
            return value * 28.3495;
        }
        return value; // If destination unit is unknown, return input value
    }

    private static double convertFromTon(double value, String destinationUnit) {
        if (destinationUnit.equals("kg")) {
            return value * 907.185;
        }
        return value; // If destination unit is unknown, return input value
    }

    // Temperature Conversions
    public static double convertTemperature(double value, String sourceUnit, String destinationUnit) {
        switch (sourceUnit) {
            case "C":
                return convertFromCelsius(value, destinationUnit);
            case "F":
                return convertFromFahrenheit(value, destinationUnit);
            case "K":
                return convertFromKelvin(value, destinationUnit);
            default:
                return value; // If source unit is unknown, return input value
        }
    }

    private static double convertFromCelsius(double value, String destinationUnit) {
        switch (destinationUnit) {
            case "F":
                return (value * 1.8) + 32;
            case "K":
                return value + 273.15;
            default:
                return value; // If destination unit is unknown, return input value
        }
    }

    private static double convertFromFahrenheit(double value, String destinationUnit) {
        switch (destinationUnit) {
            case "C":
                return (value - 32) / 1.8;
            case "K":
                return (value + 459.67) * 5 / 9;
            default:
                return value; // If destination unit is unknown, return input value
        }
    }

    private static double convertFromKelvin(double value, String destinationUnit) {
        switch (destinationUnit) {
            case "C":
                return value - 273.15;
            case "F":
                return (value * 9 / 5) - 459.67;
            default:
                return value; // If destination unit is unknown, return input value
        }
    }
}


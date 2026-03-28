package dev.joaquincoronado.betterme.tax.service.taxcalculator;

public interface TaxStrategy {
    boolean isApplicable(String zoneType);
    double calculate(double value);
}

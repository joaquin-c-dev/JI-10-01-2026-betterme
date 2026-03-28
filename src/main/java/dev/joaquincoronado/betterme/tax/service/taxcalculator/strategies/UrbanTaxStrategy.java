package dev.joaquincoronado.betterme.tax.service.taxcalculator.strategies;

import dev.joaquincoronado.betterme.tax.service.taxcalculator.TaxStrategy;
import org.springframework.stereotype.Component;

@Component
public class UrbanTaxStrategy implements TaxStrategy {
    @Override
    public boolean isApplicable(String zoneType) {
        return zoneType.equals("URBAN");
    }

    @Override
    public double calculate(double value) {
        return value * 0.16;
    }
}

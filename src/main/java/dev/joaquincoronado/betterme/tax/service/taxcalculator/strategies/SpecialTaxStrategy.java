package dev.joaquincoronado.betterme.tax.service.taxcalculator.strategies;

import dev.joaquincoronado.betterme.tax.service.taxcalculator.TaxStrategy;
import org.springframework.stereotype.Component;

@Component
public class SpecialTaxStrategy implements TaxStrategy {
    @Override
    public boolean isApplicable(String zoneType) {
        return zoneType.equals("SPECIAL");
    }

    @Override
    public double calculate(double value) {
        return value * 0.01;
    }
}

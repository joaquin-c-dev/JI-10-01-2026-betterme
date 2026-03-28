package dev.joaquincoronado.betterme.tax.service.taxcalculator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaxCalculator {

    private final List<TaxStrategy> taxStrategies;

    public double calculate(double value, String zoneType) {
        return taxStrategies.stream()
                .filter(strategy -> strategy.isApplicable(zoneType))
                .findFirst()
                .map(strategy -> strategy.calculate(value))
                .orElse(0.0);
    }
}

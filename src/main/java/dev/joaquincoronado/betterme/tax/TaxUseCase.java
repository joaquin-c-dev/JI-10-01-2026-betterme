package dev.joaquincoronado.betterme.tax;

import dev.joaquincoronado.betterme.tax.service.taxcalculator.TaxCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaxUseCase {

    private final TaxCalculator taxCalculator;

    public double calculateWithStrategy(double value, String zoneType) {
        return this.taxCalculator.calculate(value, zoneType);
    }

    public double calculateTax(double value, String zoneType) {
        double response = 0.0;
        if(zoneType.equals("URBAN")){
            response = value * 0.16;
        } else if(zoneType.equals("RURAL")){
            response = value * 0.08;
        } else if(zoneType.equals("INDUSTRIAL")){
            response = value * 0.20;
        }
        return response;
    }
}

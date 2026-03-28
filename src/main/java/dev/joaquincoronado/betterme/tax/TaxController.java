package dev.joaquincoronado.betterme.tax;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tax/v1")
public class TaxController {

    private final TaxUseCase taxService;

    public record TaxResponse(double tax){}

    @RequestMapping("/tax")
    public TaxResponse calculateTax(
        @RequestParam double value,
        @RequestParam String zoneType
    ){
        double tax = this.taxService.calculateWithStrategy(value, zoneType);
        return new TaxResponse(tax);
    }
}

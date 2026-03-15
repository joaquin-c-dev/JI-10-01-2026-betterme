package dev.joaquincoronado.betterme.dailyrecord.model.entity.inner;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class NutritionEntity {
    @Column(name = "nut_breakfast")
    private Boolean breakfast;

    @Column(name = "nut_snack_one")
    private Boolean snackOne;

    @Column(name = "nut_meal")
    private Boolean lunch;

    @Column(name = "nut_snack_two")
    private Boolean snackTwo;

    @Column(name = "nut_dinner")
    private Boolean dinner;
}

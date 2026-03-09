package dev.joaquincoronado.betterme.dailyrecord.model.dto.inner;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Nutrition {
    private Boolean breakfast;
    private Boolean snackOne;
    private Boolean lunch;
    private Boolean snackTwo;
    private Boolean dinner;
}

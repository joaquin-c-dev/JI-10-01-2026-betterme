package dev.joaquincoronado.betterme.dailyrecord.model.dto.inner;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Nutrition {
    private Boolean breakfast = false;
    private Boolean snackOne = false;
    private Boolean lunch = false;
    private Boolean snackTwo = false;
    private Boolean dinner = false;
}

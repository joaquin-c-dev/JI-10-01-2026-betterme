package dev.joaquincoronado.betterme.dailyrecord.model.dto.inner;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Score {
    private Double nutrition;
    private Double exercise;
    private Double sleep;
    private Double hydration;
    private Double total;
}

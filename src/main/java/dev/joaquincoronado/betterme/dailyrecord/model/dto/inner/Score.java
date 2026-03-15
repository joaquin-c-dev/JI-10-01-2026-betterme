package dev.joaquincoronado.betterme.dailyrecord.model.dto.inner;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    private Double nutrition;
    private Double exercise;
    private Double sleep;
    private Double hydration;
    private Double total;
}

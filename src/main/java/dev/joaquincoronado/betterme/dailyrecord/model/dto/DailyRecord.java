package dev.joaquincoronado.betterme.dailyrecord.model.dto;

import dev.joaquincoronado.betterme.dailyrecord.model.dto.inner.Nutrition;
import dev.joaquincoronado.betterme.dailyrecord.model.dto.inner.Score;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyRecord {
    private Long id;
    private Long userId;

    private LocalDateTime registrationDate;

    private Nutrition nutrition;
    private Double exerciseMinutes;
    private Double hydrationML;
    private Double sleepMinutes;

    private Score score;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

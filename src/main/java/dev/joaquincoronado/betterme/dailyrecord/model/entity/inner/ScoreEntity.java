package dev.joaquincoronado.betterme.dailyrecord.model.entity.inner;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class ScoreEntity {
    @Column(name = "score_nutrition")
    private Double nutrition;
    @Column(name = "score_exercise")
    private Double exercise;
    @Column(name = "score_sleep")
    private Double sleep;
    @Column(name = "score_hydration")
    private Double hydration;
    @Column(name = "score_total")
    private Double total;
}

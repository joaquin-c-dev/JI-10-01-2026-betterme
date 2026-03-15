package dev.joaquincoronado.betterme.dailyrecord.model.entity;

import dev.joaquincoronado.betterme.dailyrecord.model.entity.inner.NutritionEntity;
import dev.joaquincoronado.betterme.dailyrecord.model.entity.inner.ScoreEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "daily_records")
public class DailyRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Embedded
    private NutritionEntity nutrition;

    //@Column(name = "exercise_minutes", precision = 5, scale = 2)
    @Column(name = "exercise_minutes")
    private Double exerciseMinutes;

    //@Column(name = "hydration_ml", precision = 5, scale = 2)
    @Column(name = "hydration_ml")
    private Double hydrationML;

    //@Column(name = "sleep_minutes", precision = 5, scale = 2)
    @Column(name = "sleep_minutes")
    private Double sleepMinutes;

    @Embedded
    private ScoreEntity score;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

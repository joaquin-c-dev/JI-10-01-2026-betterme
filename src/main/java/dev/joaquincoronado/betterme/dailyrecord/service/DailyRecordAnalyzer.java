package dev.joaquincoronado.betterme.dailyrecord.service;

import dev.joaquincoronado.betterme.dailyrecord.model.dto.DailyRecord;
import dev.joaquincoronado.betterme.dailyrecord.model.dto.inner.Nutrition;
import dev.joaquincoronado.betterme.dailyrecord.model.dto.inner.Score;
import org.springframework.stereotype.Service;

@Service
public class DailyRecordAnalyzer {

    private static final double EXERCISE_TARGET_MINUTES = 120;
    private static final double SLEEP_TARGET_MINUTES = 480;
    private static final double HYDRATION_TARGET_ML = 2_000;
    private static final double NUTRITION_MEALS = 5;

    public void analyze(DailyRecord dailyRecord){
        double nutritionScore = this.calculateNutritionScore(dailyRecord.getNutrition());
        double exerciseScore = this.calculateProportionalScore(dailyRecord.getExerciseMinutes(), EXERCISE_TARGET_MINUTES);
        double sleepScore = this.calculateProportionalScore(dailyRecord.getSleepMinutes(), SLEEP_TARGET_MINUTES);
        double hydrationScore = this.calculateProportionalScore(dailyRecord.getHydrationML(), HYDRATION_TARGET_ML);
        double totalScore = (nutritionScore + exerciseScore + sleepScore + hydrationScore) / 4;

        Score score = Score.builder()
            .nutrition(nutritionScore)
            .exercise(exerciseScore)
            .sleep(sleepScore)
            .hydration(hydrationScore)
            .total(totalScore)
            .build();

        dailyRecord.setScore(score);
    }

    private double calculateNutritionScore(Nutrition nutrition){
        if(nutrition == null) return 0;

        double points = 0;
        points += nutrition.getBreakfast() ? 100 : 0;
        points += nutrition.getSnackOne() ? 100 : 0;
        points += nutrition.getLunch() ? 100 : 0;
        points += nutrition.getSnackTwo() ? 100 : 0;
        points += nutrition.getDinner() ? 100 : 0;
        return points / NUTRITION_MEALS;
    }

    private double calculateProportionalScore(Double value, double target){
        if(value == null || value <= 0) return 0;
        if(value >= target) return 100;
        return (value / target) * 100;
    }
}

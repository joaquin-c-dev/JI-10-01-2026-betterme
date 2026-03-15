package dev.joaquincoronado.betterme.dailyrecord.model.mapper;

import dev.joaquincoronado.betterme.dailyrecord.model.dto.DailyRecord;
import dev.joaquincoronado.betterme.dailyrecord.model.dto.inner.Nutrition;
import dev.joaquincoronado.betterme.dailyrecord.model.dto.inner.Score;
import dev.joaquincoronado.betterme.dailyrecord.model.entity.DailyRecordEntity;
import dev.joaquincoronado.betterme.dailyrecord.model.entity.inner.NutritionEntity;
import dev.joaquincoronado.betterme.dailyrecord.model.entity.inner.ScoreEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IDailyRecordMapper {
    DailyRecordEntity toEntity(DailyRecord model);
    DailyRecord toModel(DailyRecordEntity entity);

    ScoreEntity toEntity(Score model);
    Score toModel(ScoreEntity entity);

    NutritionEntity toEntity(Nutrition model);
    Nutrition toModel(NutritionEntity entity);
}

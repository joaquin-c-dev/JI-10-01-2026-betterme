package dev.joaquincoronado.betterme.dailyrecord.dao;

import dev.joaquincoronado.betterme.dailyrecord.model.dto.DailyRecord;

import java.time.LocalDateTime;
import java.util.List;

public interface IDailyRecordDao {
    List<DailyRecord> listByUserIdAndRegistrationDateRange(
        long userId,
        LocalDateTime startDate,
        LocalDateTime endDate
    );

    DailyRecord getById(long id);

    void create(DailyRecord dailyRecord);

    void update(DailyRecord dailyRecord);
}

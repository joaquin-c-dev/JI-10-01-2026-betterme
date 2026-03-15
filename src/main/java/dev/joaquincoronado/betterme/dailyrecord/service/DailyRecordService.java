package dev.joaquincoronado.betterme.dailyrecord.service;

import dev.joaquincoronado.betterme.dailyrecord.dao.IDailyRecordDao;
import dev.joaquincoronado.betterme.dailyrecord.model.dto.DailyRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyRecordService {

    private final IDailyRecordDao dailyRecordDao;

    public List<DailyRecord> listByUserId(long userId, LocalDateTime startDate, LocalDateTime endDate){
        return this.dailyRecordDao.listByUserIdAndRegistrationDateRange(userId, startDate, endDate);
    }

    public DailyRecord getByRegistrationDate(long userId, LocalDateTime registrationDate){
        LocalDateTime startDate =  registrationDate.toLocalDate().atStartOfDay();
        LocalDateTime endDate = registrationDate.toLocalDate().atTime(23, 59, 59, 999_999_999);
        List<DailyRecord> records = this.dailyRecordDao.listByUserIdAndRegistrationDateRange(userId, startDate, endDate);
        if(records.isEmpty()) return null;
        return records.getFirst();
    }

    public DailyRecord getById(long id){
        return this.dailyRecordDao.getById(id);
    }

    public void create(DailyRecord dailyRecord){
        dailyRecord.setCreatedAt(LocalDateTime.now());
        this.dailyRecordDao.create(dailyRecord);
    }

    public void update(DailyRecord dailyRecord){
        dailyRecord.setUpdatedAt(LocalDateTime.now());
        this.dailyRecordDao.update(dailyRecord);
    }

    public void mergeToUpdate(DailyRecord current, DailyRecord update){
        if(update.getScore() != null){
            current.setScore(update.getScore());
        }
        if(update.getNutrition() != null){
            current.setNutrition(update.getNutrition());
        }
        if(update.getExerciseMinutes() != null){
            current.setExerciseMinutes(update.getExerciseMinutes());
        }
        if(update.getHydrationML() != null){
            current.setHydrationML(update.getHydrationML());
        }
        if(update.getSleepMinutes() != null){
            current.setSleepMinutes(update.getSleepMinutes());
        }
    }

}

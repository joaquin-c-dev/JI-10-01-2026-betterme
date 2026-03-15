package dev.joaquincoronado.betterme.dailyrecord.dao.springdatajpa;

import dev.joaquincoronado.betterme.dailyrecord.model.entity.DailyRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IDailyRecordRepository extends JpaRepository<DailyRecordEntity, Long> {
    List<DailyRecordEntity> findAllByUserIdAndRegistrationDateBetween(
        long userId,
        LocalDateTime startDate,
        LocalDateTime endDate
    );
}

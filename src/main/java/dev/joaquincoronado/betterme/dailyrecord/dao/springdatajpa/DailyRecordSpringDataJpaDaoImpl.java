package dev.joaquincoronado.betterme.dailyrecord.dao.springdatajpa;

import dev.joaquincoronado.betterme.dailyrecord.dao.IDailyRecordDao;
import dev.joaquincoronado.betterme.dailyrecord.model.dto.DailyRecord;
import dev.joaquincoronado.betterme.dailyrecord.model.entity.DailyRecordEntity;
import dev.joaquincoronado.betterme.dailyrecord.model.mapper.IDailyRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Repository("dailyRecordSpringJpa")
public class DailyRecordSpringDataJpaDaoImpl implements IDailyRecordDao {

    private final IDailyRecordRepository dailyRecordRepository;
    private final IDailyRecordMapper mapper;

    @Override
    public List<DailyRecord> listByUserIdAndRegistrationDateRange(long userId, LocalDateTime startDate, LocalDateTime endDate) {

        List<DailyRecordEntity> entities = this.dailyRecordRepository.findAllByUserIdAndRegistrationDateBetween(
            userId,
            startDate,
            endDate
        );

        return entities.stream().map(this.mapper::toModel).toList();
    }

    @Override
    public DailyRecord getById(long id) {
        DailyRecordEntity entity = this.dailyRecordRepository.findById(id).orElse(null);
        return this.mapper.toModel(entity);
    }

    @Override
    public void create(DailyRecord dailyRecord) {
        DailyRecordEntity entity = this.mapper.toEntity(dailyRecord);
        this.dailyRecordRepository.save(entity);
        dailyRecord.setId(entity.getId());
    }

    @Override
    public void update(DailyRecord dailyRecord) {
        DailyRecordEntity entity = this.mapper.toEntity(dailyRecord);
        this.dailyRecordRepository.save(entity);
    }
}

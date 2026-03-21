package dev.joaquincoronado.betterme.dailyrecord.usecase;

import dev.joaquincoronado.betterme.dailyrecord.model.dto.DailyRecord;
import dev.joaquincoronado.betterme.dailyrecord.service.DailyRecordAnalyzer;
import dev.joaquincoronado.betterme.dailyrecord.service.DailyRecordService;
import dev.joaquincoronado.betterme.shared.exception.BadRequestException;
import dev.joaquincoronado.betterme.shared.exception.ForbiddenException;
import dev.joaquincoronado.betterme.shared.exception.NotFoundException;
import dev.joaquincoronado.betterme.shared.model.RequesterInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyRecordUseCase {
    private final DailyRecordAnalyzer dailyRecordAnalyzer;
    private final DailyRecordService dailyRecordService;

    public List<DailyRecord> listByUserUseCase(
        RequesterInfo requester,
        LocalDateTime startDate,
        LocalDateTime endDate
    ){
        return this.dailyRecordService.listByUserId(requester.getId(), startDate, endDate);
    }

    public DailyRecord processDailyProgressUseCase(RequesterInfo requester, DailyRecord dailyRecord){
        if(dailyRecord == null) throw new BadRequestException("dailyRecord is required");
        if(dailyRecord.getRegistrationDate() == null){
            dailyRecord.setRegistrationDate(LocalDateTime.now());
        }

        DailyRecord currentDailyRecord = this.dailyRecordService.getByRegistrationDate(
            requester.getId(),
            dailyRecord.getRegistrationDate()
        );

        //create
        if(currentDailyRecord == null){
            dailyRecord.setUserId(requester.getId());
            this.dailyRecordService.create(dailyRecord);
            currentDailyRecord = dailyRecord;
        }
        //update
        else {
            this.dailyRecordService.mergeToUpdate(currentDailyRecord, dailyRecord);
            this.dailyRecordService.update(currentDailyRecord);
        }

        return currentDailyRecord;
    }

    public DailyRecord analyzeDailyRecordUseCase(RequesterInfo requester, long dailyRecordId){
        DailyRecord currentDailyRecord = this.dailyRecordService.getById(dailyRecordId);
        if(currentDailyRecord == null) throw new NotFoundException("dailyRecord not found");

        if(!requester.getId().equals(currentDailyRecord.getUserId())){
            throw new ForbiddenException("user is not owner of dailyRecord");
        }

        this.dailyRecordAnalyzer.analyze(currentDailyRecord);
        this.dailyRecordService.update(currentDailyRecord);

        return currentDailyRecord;
    }

}

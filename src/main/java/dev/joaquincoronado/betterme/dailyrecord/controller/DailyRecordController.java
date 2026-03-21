package dev.joaquincoronado.betterme.dailyrecord.controller;

import dev.joaquincoronado.betterme.auth.model.BettermeAuth;
import dev.joaquincoronado.betterme.dailyrecord.model.dto.DailyRecord;
import dev.joaquincoronado.betterme.dailyrecord.usecase.DailyRecordUseCase;
import dev.joaquincoronado.betterme.shared.model.RequesterInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dailyRecord/v1")
public class DailyRecordController {
    private final DailyRecordUseCase dailyRecordUseCase;

    @GetMapping("/dailyRecord")
    public List<DailyRecord> listByUser(
        @CurrentSecurityContext(expression = "authentication") BettermeAuth bettermeAuth,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ){
        RequesterInfo requesterInfo = bettermeAuth.getRequesterInfo();
        return this.dailyRecordUseCase.listByUserUseCase(requesterInfo, startDate, endDate);
    }

    @PostMapping("/dailyRecord/process")
    public DailyRecord processDailyProgress(
        @CurrentSecurityContext(expression = "authentication") BettermeAuth bettermeAuth,
        @RequestBody DailyRecord dailyRecord
    ){
        RequesterInfo requesterInfo = bettermeAuth.getRequesterInfo();
        return this.dailyRecordUseCase.processDailyProgressUseCase(requesterInfo, dailyRecord);
    }

    @GetMapping("/dailyRecord/analyze")
    public DailyRecord analyzeDailyRecord(
        @CurrentSecurityContext(expression = "authentication") BettermeAuth bettermeAuth,
        @RequestParam Long dailyRecordId
    ){
       RequesterInfo requesterInfo = bettermeAuth.getRequesterInfo();
       return this.dailyRecordUseCase.analyzeDailyRecordUseCase(requesterInfo, dailyRecordId);
    }
}

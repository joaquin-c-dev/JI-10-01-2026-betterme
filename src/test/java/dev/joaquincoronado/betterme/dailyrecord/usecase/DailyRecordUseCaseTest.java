package dev.joaquincoronado.betterme.dailyrecord.usecase;

import dev.joaquincoronado.betterme.dailyrecord.model.dto.DailyRecord;
import dev.joaquincoronado.betterme.dailyrecord.service.DailyRecordAnalyzer;
import dev.joaquincoronado.betterme.dailyrecord.service.DailyRecordService;
import dev.joaquincoronado.betterme.shared.exception.NotFoundException;
import dev.joaquincoronado.betterme.shared.model.RequesterInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DailyRecordUseCaseTest {

    @Mock
    private DailyRecordService dailyRecordService;

    @Spy
    private DailyRecordAnalyzer dailyRecordAnalyzer;

    @InjectMocks
    private DailyRecordUseCase dailyRecordUseCase;

    @Test
    void processDailyProgressUseCase_shouldCreateWhenNotExists(){
        RequesterInfo requester = new RequesterInfo();
        requester.setId(7L);

        DailyRecord dailyRecord = new DailyRecord();
        LocalDateTime registrationDate = LocalDateTime.of(2026, 3, 9, 14, 8,0);
        dailyRecord.setRegistrationDate(registrationDate);

        when(dailyRecordService.getByRegistrationDate(7L, registrationDate)).thenReturn(null);

        DailyRecord dailyRecordCreated = this.dailyRecordUseCase.processDailyProgressUseCase(requester, dailyRecord);

        assertNotNull(dailyRecordCreated);
        assertEquals(7L, dailyRecordCreated.getUserId());

        verify(dailyRecordService).create(dailyRecord);
        verify(dailyRecordService, never()).update(any());
    }

    @Test
    void analyzeDailyRecordUseCase_shouldThrowNotFound(){
        RequesterInfo requester = new RequesterInfo();
        requester.setId(7L);

        when(dailyRecordService.getById(90L)).thenReturn(null);

        assertThrows(
            NotFoundException.class,
            () -> this.dailyRecordUseCase.analyzeDailyRecordUseCase(requester, 90L)
        );
        verify(dailyRecordAnalyzer, never()).analyze(any());
        verify(dailyRecordService, never()).update(any());
    }


}
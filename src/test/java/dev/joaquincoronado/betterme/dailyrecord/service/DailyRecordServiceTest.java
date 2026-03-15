package dev.joaquincoronado.betterme.dailyrecord.service;

import dev.joaquincoronado.betterme.dailyrecord.model.dto.DailyRecord;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Rollback
@Transactional
@SpringBootTest
class DailyRecordServiceTest {

    @Autowired
    private DailyRecordService dailyRecordService;

    @Test
    void listByUserId() {

    }

    @Test
    void getByRegistrationDate() {
    }

    @Test
    void getById() {
        //create the daily record
        DailyRecord dailyRecord = new DailyRecord();
        dailyRecord.setUserId(9999999999L);
        dailyRecord.setRegistrationDate(LocalDateTime.now());
        this.dailyRecordService.create(dailyRecord);

        //get the daily record by id
        DailyRecord currentRecord = this.dailyRecordService.getById(dailyRecord.getId());

        //validate the daily record
        assertNotNull(currentRecord);
        assertEquals(currentRecord.getId(), dailyRecord.getId());
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }
}
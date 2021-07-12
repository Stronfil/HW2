package ru.fomin.free_progect.mappers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

@SpringBootTest
@DirtiesContext
@ActiveProfiles("test")
@FieldDefaults(level = AccessLevel.PRIVATE)
class DataMapperTest {

    @Autowired
    DataMapper dataMapper;

    @Test
    public void convertToString() {
        String expectedTime = "10.07.2021 01:30";
        LocalDateTime time = LocalDateTime.of(2021, 7, 10, 1, 30);
        Assertions.assertEquals(expectedTime, dataMapper.convertToString(time));
    }

}
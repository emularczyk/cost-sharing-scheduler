package com.duo.costsharingscheduler.model;

import com.duo.costsharingscheduler.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SchedulerParametersTest {

    private Scheduler createDefaultScheduler() {
        ValueField valueField1 = ValueField.builder()
                .valueField(BigDecimal.valueOf(50.0))
                .build();
        ValueField valueField2 = ValueField.builder()
                .valueField(BigDecimal.valueOf(50.0))
                .build();
        ValueField valueField3 = ValueField.builder()
                .valueField(BigDecimal.valueOf(50.0))
                .build();
        ValueField valueField4 = ValueField.builder()
                .valueField(BigDecimal.valueOf(50.0))
                .build();
        ValueField valueField5 = ValueField.builder()
                .valueField(BigDecimal.valueOf(100.0))
                .build();
        ValueField valueField6 = ValueField.builder()
                .valueField(BigDecimal.valueOf(100.0))
                .build();

        Column column1 = Column.builder()
                .title("Transport costs")
                .build();
        Column column2 = Column.builder()
                .title("Hotel costs")
                .build();
        Column column3 = Column.builder()
                .title("Attractions costs")
                .build();
        Row row1 = Row.builder()
                .title("Adam")
                .valueFieldList(List.of(valueField1, valueField2, valueField3))
                .build();
        Row row2 = Row.builder()
                .title("Ewa")
                .valueFieldList(List.of(valueField4, valueField5, valueField6))
                .build();

        return Scheduler.builder()
                .title("School trip scheduler")
                .columns(List.of(column1, column2, column3))
                .rows(List.of(row1, row2))
                .build();
    }

    @Test
    public void correctSchedulerParameters(){
        //given
        Scheduler scheduler = createDefaultScheduler();
        SchedulerParameters schedulerParameters = new SchedulerParameters(scheduler);

        List<BigDecimal> expectedSumOfColumns = new ArrayList<>(Arrays.asList(BigDecimal.valueOf(100.0), BigDecimal.valueOf(150.0), BigDecimal.valueOf(150.0)));
        List<BigDecimal> expectedMeanOfColumns = new ArrayList<>(Arrays.asList(BigDecimal.valueOf(50.0), BigDecimal.valueOf(75.0), BigDecimal.valueOf(75.0)));

        //result
        assertThat(schedulerParameters.getSumOfColumns()).isEqualTo(expectedSumOfColumns);
        assertThat(schedulerParameters.getMeanOfColumns()).isEqualTo(expectedMeanOfColumns);
    }

    @Test
    public void incorrectSchedulerParameters(){
        //given
        Scheduler scheduler = createDefaultScheduler();
        SchedulerParameters schedulerParameters = new SchedulerParameters(scheduler);

        List<BigDecimal> expectedSumOfColumns = new ArrayList<>(Arrays.asList(BigDecimal.valueOf(100), BigDecimal.valueOf(150), BigDecimal.valueOf(150)));
        List<BigDecimal> expectedMeanOfColumns = new ArrayList<>(Arrays.asList(BigDecimal.valueOf(50), BigDecimal.valueOf(75), BigDecimal.valueOf(75)));

        //result
        assertThat(schedulerParameters.getSumOfColumns()).isNotEqualTo(expectedSumOfColumns);
        assertThat(schedulerParameters.getMeanOfColumns()).isNotEqualTo(expectedMeanOfColumns);
    }

}

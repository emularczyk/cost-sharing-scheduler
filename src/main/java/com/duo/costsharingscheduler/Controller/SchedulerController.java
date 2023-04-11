package com.duo.costsharingscheduler;

import com.duo.costsharingscheduler.Model.Column;
import com.duo.costsharingscheduler.Model.Row;
import com.duo.costsharingscheduler.Model.Scheduler;
import com.duo.costsharingscheduler.Model.ValueField;
import com.duo.costsharingscheduler.Repository.ColumnRepository;
import com.duo.costsharingscheduler.Repository.RowRepository;
import com.duo.costsharingscheduler.Repository.SchedulerRepository;
import com.duo.costsharingscheduler.Repository.ValueFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class SchedulerController {

    @Autowired
    private SchedulerRepository schedulerRepository;
    @Autowired
    private RowRepository rowRepository;
    @Autowired
    private ColumnRepository columnRepository;
    @Autowired
    private ValueFieldRepository valueFieldRepository;

    @GetMapping ("/generateScheduler")
    public void creteDefaultScheduler(){
        ValueField valueField1 = valueFieldRepository.save(ValueField.builder()
                .valueFiled(BigDecimal.valueOf(100.0))
                .build());
        ValueField valueField2 = valueFieldRepository.save(ValueField.builder()
                .valueFiled(BigDecimal.valueOf(50.0))
                .build());
        ValueField valueField3 = valueFieldRepository.save(ValueField.builder()
                .valueFiled(BigDecimal.valueOf(30.0))
                .build());
        ValueField valueField4 = valueFieldRepository.save(ValueField.builder()
                .valueFiled(BigDecimal.valueOf(100.0))
                .build());
        ValueField valueField5 = valueFieldRepository.save(ValueField.builder()
                .valueFiled(BigDecimal.valueOf(50.0))
                .build());
        ValueField valueField6 = valueFieldRepository.save(ValueField.builder()
                .valueFiled(BigDecimal.valueOf(60.0))
                .build());

        Column column1 = columnRepository.save(Column.builder()
                .title("Transport costs")
                .build());
        Column column2 = columnRepository.save(Column.builder()
                .title("Hotel costs")
                .build());
        Column column3 = columnRepository.save(Column.builder()
                .title("Attractions costs")
                .build());
        Row row1 = rowRepository.save(Row.builder()
                .title("Adam")
                .valueFieldList(List.of(valueField1, valueField2,valueField3))
                .build());
        Row row2 = rowRepository.save(Row.builder()
                .title("Ewa")
                .valueFieldList(List.of(valueField4, valueField5,valueField6))
                .build());

        Scheduler defaultScheduler = Scheduler.builder()
                .title("School trip scheduler")
                .columns(List.of(column1, column2,column3))
                .rows(List.of(row1, row2))
                .build();

        schedulerRepository.save(defaultScheduler);
    }

    @GetMapping("/scheduler/{schedulerId}")
    public String getScheduler(final Model model, @PathVariable("schedulerId") final Long schedulerId) {


        Scheduler scheduler = schedulerRepository.findById(schedulerId).orElse(null);
        model.addAttribute("scheduler", scheduler);
        return "scheduler";
    }

    @GetMapping("/scheduler")
    public String getScheduler(final Model model) {
        ValueField valueField1 = valueFieldRepository.save(ValueField.builder()
                .valueFiled(BigDecimal.valueOf(100.0))
                .build());
        ValueField valueField2 = valueFieldRepository.save(ValueField.builder()
                .valueFiled(BigDecimal.valueOf(50.0))
                .build());
        ValueField valueField3 = valueFieldRepository.save(ValueField.builder()
                .valueFiled(BigDecimal.valueOf(30.0))
                .build());
        ValueField valueField4 = valueFieldRepository.save(ValueField.builder()
                .valueFiled(BigDecimal.valueOf(100.0))
                .build());
        ValueField valueField5 = valueFieldRepository.save(ValueField.builder()
                .valueFiled(BigDecimal.valueOf(50.0))
                .build());
        ValueField valueField6 = valueFieldRepository.save(ValueField.builder()
                .valueFiled(BigDecimal.valueOf(60.0))
                .build());

        Column column1 = columnRepository.save(Column.builder()
                .title("Transport costs")
                .build());
        Column column2 = columnRepository.save(Column.builder()
                .title("Hotel costs")
                .build());
        Column column3 = columnRepository.save(Column.builder()
                .title("Attractions costs")
                .build());
        Row row1 = rowRepository.save(Row.builder()
                .title("Adam")
                .valueFieldList(List.of(valueField1, valueField2,valueField3))
                .build());
        Row row2 = rowRepository.save(Row.builder()
                .title("Ewa")
                .valueFieldList(List.of(valueField4, valueField5,valueField6))
                .build());

        Scheduler defaultScheduler = Scheduler.builder()
                .title("School trip scheduler")
                .columns(List.of(column1, column2,column3))
                .rows(List.of(row1, row2))
                .build();

        model.addAttribute("scheduler", defaultScheduler);
        return "scheduler";
    }
}

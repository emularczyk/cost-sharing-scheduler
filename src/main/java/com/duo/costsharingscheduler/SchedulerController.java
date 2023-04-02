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


    @GetMapping("/scheduler")
    public String showScheduler(Model model) {

        Scheduler scheduler;
        Column column1;
        Column column2;
        Row row1;
        Row row2;

        ValueField valueField1 = valueFieldRepository.save(ValueField.builder()
                .valueFiled(BigDecimal.valueOf(1))
                .build());
        ValueField valueField2 = valueFieldRepository.save(ValueField.builder()
                .valueFiled(BigDecimal.valueOf(2))
                .build());
        ValueField valueField3 = valueFieldRepository.save(ValueField.builder()
                .valueFiled(BigDecimal.valueOf(3))
                .build());
        ValueField valueField4 = valueFieldRepository.save(ValueField.builder()
                .valueFiled(BigDecimal.valueOf(4))
                .build());

        column1 = columnRepository.save(Column.builder()
                .title("column1")
                .build());
        column2 = columnRepository.save(Column.builder()
                .title("column2")
                .build());
        row1 = rowRepository.save(Row.builder()
                .title("row1")
                .valueFieldList(List.of(valueField1, valueField2))
                .build());
        row2 = rowRepository.save(Row.builder()
                .title("row2")
                .valueFieldList(List.of(valueField3, valueField4))
                .build());

        scheduler = schedulerRepository.save(Scheduler.builder()
                .title("scheduler1")
                .columns(List.of(column1, column2))
                .rows(List.of(row1, row2))
                .build());

        Scheduler schedulerFromDatabase = schedulerRepository.findById(Long.valueOf(1)).stream().findFirst().orElse(null);
        model.addAttribute("scheduler", scheduler);
        model.addAttribute("scheduler2", schedulerFromDatabase);
        System.out.println(scheduler);
        System.out.println(schedulerFromDatabase);
        return "scheduler";
    }
}

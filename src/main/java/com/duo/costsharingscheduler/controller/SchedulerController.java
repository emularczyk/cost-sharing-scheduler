package com.duo.costsharingscheduler.controller;

import com.duo.costsharingscheduler.exceptions.ItemNotFoundException;
import com.duo.costsharingscheduler.model.Column;
import com.duo.costsharingscheduler.model.Row;
import com.duo.costsharingscheduler.model.Scheduler;
import com.duo.costsharingscheduler.model.ValueField;
import com.duo.costsharingscheduler.repository.ColumnRepository;
import com.duo.costsharingscheduler.repository.RowRepository;
import com.duo.costsharingscheduler.repository.SchedulerRepository;
import com.duo.costsharingscheduler.repository.ValueFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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


    @GetMapping("/generateScheduler")
    public void creteDefaultScheduler() {
        ValueField valueField1 = valueFieldRepository.save(ValueField.builder()
                .valueField(BigDecimal.valueOf(100.0))
                .build());
        ValueField valueField2 = valueFieldRepository.save(ValueField.builder()
                .valueField(BigDecimal.valueOf(50.0))
                .build());
        ValueField valueField3 = valueFieldRepository.save(ValueField.builder()
                .valueField(BigDecimal.valueOf(30.0))
                .build());
        ValueField valueField4 = valueFieldRepository.save(ValueField.builder()
                .valueField(BigDecimal.valueOf(100.0))
                .build());
        ValueField valueField5 = valueFieldRepository.save(ValueField.builder()
                .valueField(BigDecimal.valueOf(50.0))
                .build());
        ValueField valueField6 = valueFieldRepository.save(ValueField.builder()
                .valueField(BigDecimal.valueOf(60.0))
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
                .valueFieldList(List.of(valueField1, valueField2, valueField3))
                .build());
        Row row2 = rowRepository.save(Row.builder()
                .title("Ewa")
                .valueFieldList(List.of(valueField4, valueField5, valueField6))
                .build());

        Scheduler defaultScheduler = Scheduler.builder()
                .title("School trip scheduler")
                .columns(List.of(column1, column2, column3))
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

    @GetMapping("/scheduler/edit/{schedulerId}")
    public String getEditScheduler(final Model model, @PathVariable("schedulerId") final Long schedulerId) {
        Scheduler scheduler = schedulerRepository.findById(schedulerId).orElse(null);
        model.addAttribute("scheduler", scheduler);
        return "scheduler-edit";
    }

    @PostMapping("/scheduler/save")
    public String saveScheduler(@ModelAttribute Scheduler scheduler) {
        Optional<Scheduler> optionalScheduler = schedulerRepository.findById(scheduler.getId());
        if (optionalScheduler.isPresent()) {
            Scheduler schedulerFromDB = optionalScheduler.get();
            schedulerFromDB.setTitle(scheduler.getTitle());
            schedulerFromDB.setColumns(scheduler.getColumns());
            schedulerFromDB.setRows(scheduler.getRows());
            schedulerRepository.save(schedulerFromDB);
        } else {
            throw new ItemNotFoundException("Scheduler with id " + scheduler.getId());
        }
        return "redirect:/scheduler/" + scheduler.getId();
    }
}

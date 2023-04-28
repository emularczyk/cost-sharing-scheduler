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
import com.duo.costsharingscheduler.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    @Autowired
    private SchedulerService schedulerService;


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

    @GetMapping("/schedulers")
    public String getMainPage(Model model) {
        List<Scheduler> schedulers = schedulerService.findAllSchedulers();
        model.addAttribute("schedulers", schedulers);

        return "schedulers";
    }

    @PostMapping("/scheduler/addNew")
    public String newScheduler(String schedulerTitle) {
        schedulerService.addScheduler(schedulerTitle);
        return "redirect:/schedulers";
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

    @PostMapping("/scheduler/{schedulerId}/delete")
    public String deleteScheduler(@PathVariable("schedulerId") final Long schedulerId){
        Optional<Scheduler> optionalScheduler = schedulerRepository.findById(schedulerId);
        if (optionalScheduler.isPresent()) {
            schedulerService.deleteScheduler(schedulerId);
        } else {
            throw new ItemNotFoundException("Can't delete scheduler with id " + schedulerId);
        }
        return "redirect:/schedulers";
    }

    @GetMapping("/scheduler/{schedulerId}/add/column")
    public String addColumn(@PathVariable("schedulerId")final Long schedulerId) {
        Scheduler scheduler = schedulerRepository.findById(schedulerId).orElse(null);
        List<Column> columnList = scheduler.getColumns();
        columnList.add(new Column());
        scheduler.setColumns(columnList);
        List<ValueField> valueList;
        for (Row row: scheduler.getRows()) {
            valueList =row.getValueFieldList();
            valueList.add(new ValueField());
            row.setValueFieldList(valueList);
        }
       schedulerRepository.save(scheduler);
        return "redirect:/scheduler/" + scheduler.getId();
    }

    @GetMapping("/scheduler/{schedulerId}/remove/column")
    public String removeColumn(@PathVariable("schedulerId")final Long schedulerId) {
        Scheduler scheduler = schedulerRepository.findById(schedulerId).orElse(null);
        List<Column> columnList = scheduler.getColumns();
        columnList.remove(columnList.size()-1);
        scheduler.setColumns(columnList);
        List<ValueField> valueList;
        for (Row row: scheduler.getRows()) {
            valueList =row.getValueFieldList();
            valueList.remove(valueList.size()-1);
            row.setValueFieldList(valueList);
        }
       schedulerRepository.save(scheduler);
        return "redirect:/scheduler/" + scheduler.getId();
    }

    @GetMapping("/scheduler/{schedulerId}/add/row")
    public String addRow(@PathVariable("schedulerId")final Long schedulerId) {
        Scheduler scheduler = schedulerRepository.findById(schedulerId).orElse(null);
        List<Row> rowList = scheduler.getRows();
        Row newRow= new Row();
        List<ValueField> valueList=new ArrayList<>();
        for (int i=0;i<scheduler.getColumns().size();i++) {
            valueList.add(new ValueField());
        }
        newRow.setValueFieldList(valueList);
        rowList.add(newRow);
        scheduler.setRows(rowList);
        schedulerRepository.save(scheduler);
        return "redirect:/scheduler/" + scheduler.getId();
    }

    @GetMapping("/scheduler/{schedulerId}/remove/row")
    public String removeRow(@PathVariable("schedulerId")final Long schedulerId) {
        Scheduler scheduler = schedulerRepository.findById(schedulerId).orElse(null);
        List<Row> rowList = scheduler.getRows();
        rowList.remove(rowList.size()-1);
        scheduler.setRows(rowList);
        schedulerRepository.save(scheduler);
        return "redirect:/scheduler/" + scheduler.getId();
    }
}

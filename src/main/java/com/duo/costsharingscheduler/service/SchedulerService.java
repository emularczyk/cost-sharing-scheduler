package com.duo.costsharingscheduler.service;

import com.duo.costsharingscheduler.model.Column;
import com.duo.costsharingscheduler.model.Row;
import com.duo.costsharingscheduler.model.Scheduler;
import com.duo.costsharingscheduler.model.ValueField;
import com.duo.costsharingscheduler.repository.SchedulerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulerService {
    @Autowired
    private SchedulerRepository schedulerRepository;

    public List<Scheduler> findAllSchedulers() {
        return (List<Scheduler>) schedulerRepository.findAll();
    }

    public void addScheduler(String title) {
        ValueField valueField = new ValueField();
        Column colum = new Column();
        Row row = new Row();
        row.setValueFieldList(List.of(valueField));
        Scheduler scheduler = Scheduler.builder()
                .title(title)
                .columns(List.of(colum))
                .rows(List.of(row))
                .build();
        schedulerRepository.save(scheduler);
    }

    public void deleteScheduler(final Long schedulerId){
        schedulerRepository.deleteById(schedulerId);
    }
}

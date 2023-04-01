package com.duo.costsharingscheduler;

import com.duo.costsharingscheduler.Model.GroupsOfCells;
import com.duo.costsharingscheduler.Model.Scheduler;
import com.duo.costsharingscheduler.Model.ValueField;
import com.duo.costsharingscheduler.Repository.GroupsOfCellsRepository;
import com.duo.costsharingscheduler.Repository.SchedulerRepository;
import com.duo.costsharingscheduler.Repository.ValueFieldRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class CostSharingSchedulerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                SpringApplication.run(CostSharingSchedulerApplication.class, args);

        SchedulerRepository schedulerRepository =
                configurableApplicationContext.getBean(SchedulerRepository.class);
        GroupsOfCellsRepository groupsOfCellsRepository =
                configurableApplicationContext.getBean(GroupsOfCellsRepository.class);
        ValueFieldRepository valueFieldRepository =
                configurableApplicationContext.getBean(ValueFieldRepository.class);


        Scheduler scheduler = new Scheduler("test");

        GroupsOfCells column1 = new GroupsOfCells("column 1", scheduler);
        GroupsOfCells column2 = new GroupsOfCells("column 2", scheduler);
        GroupsOfCells row1 = new GroupsOfCells("row 1", scheduler);
        GroupsOfCells row2 = new GroupsOfCells("row 2", scheduler);

        ValueField valueField1 = new ValueField(BigDecimal.ONE, column1, row1);
        ValueField valueField2 = new ValueField(BigDecimal.valueOf(2), column2, row1);
        ValueField valueField3 = new ValueField(BigDecimal.valueOf(3), column1, row2);
        ValueField valueField4 = new ValueField(BigDecimal.valueOf(4), column2, row2);

        schedulerRepository.save(scheduler);

        groupsOfCellsRepository.save(column1);
        groupsOfCellsRepository.save(column2);
        groupsOfCellsRepository.save(row1);
        groupsOfCellsRepository.save(row2);

        valueFieldRepository.save(valueField1);
        valueFieldRepository.save(valueField2);
        valueFieldRepository.save(valueField3);
        valueFieldRepository.save(valueField4);
    }
}

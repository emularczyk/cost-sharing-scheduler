package com.duo.costsharingscheduler.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class GroupsOfCells {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "schedulerId")
    private Scheduler scheduler;

    @OneToMany(
          //  cascade = CascadeType.ALL,
            mappedBy = "column",
            orphanRemoval = true
    )
    private List<ValueField> columnValueFieldList = new ArrayList<>();

    @OneToMany(
            //  cascade = CascadeType.ALL,
            mappedBy = "row",
            orphanRemoval = true
    )
    private List<ValueField> rowValueFieldList = new ArrayList<>();

    public GroupsOfCells() {
    }

    public GroupsOfCells(String title, Scheduler scheduler) {
        this.title = title;
        this.scheduler = scheduler;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}

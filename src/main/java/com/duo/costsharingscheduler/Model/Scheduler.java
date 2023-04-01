package com.duo.costsharingscheduler.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Scheduler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(
            mappedBy = "scheduler",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<GroupsOfCells> columns = new ArrayList<>();

    @OneToMany(
            mappedBy = "scheduler",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<GroupsOfCells> rows = new ArrayList<>();

    public Scheduler() {
    }

    public Scheduler(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<GroupsOfCells> getColumns() {
        return columns;
    }

    public void setColumns(List<GroupsOfCells> columns) {
        this.columns = columns;
    }

    public List<GroupsOfCells> getRows() {
        return rows;
    }

    public void setRows(List<GroupsOfCells> rows) {
        this.rows = rows;
    }
}

package com.duo.costsharingscheduler.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "valueField")
public class ValueField {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal valueFiled;

    @ManyToOne
    @JoinColumn(name = "columnId")
    private GroupsOfCells column;

    @ManyToOne
    @JoinColumn(name = "rowId")
    private GroupsOfCells row;

    public ValueField() {
    }

    public ValueField(BigDecimal valueFiled,GroupsOfCells column,GroupsOfCells row) {
        this.valueFiled = valueFiled;
        this.column = column;
        this.row = row;
    }

    public BigDecimal getTitle() {
        return valueFiled;
    }

    public void setTitle(BigDecimal values,GroupsOfCells column,GroupsOfCells row) {
        this.valueFiled = valueFiled;
        this.column = column;
        this.row = row;
    }

    public GroupsOfCells getColumn() {
        return column;
    }

    public void setColumn(GroupsOfCells column) {
        this.column = column;
    }

    public GroupsOfCells getRow() {
        return row;
    }

    public void setRow(GroupsOfCells row) {
        this.row = row;
    }
}

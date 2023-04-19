package com.duo.costsharingscheduler.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rows")
@Getter
@Setter
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DynamicInsert
public class Row {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "row_id")
    @ToString.Exclude
    private List<ValueField> valueFieldList = new ArrayList<>();
}

package com.duo.costsharingscheduler.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "schedulers")
@Getter
@Setter
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DynamicInsert

public class Scheduler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Column> columns = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Row> rows = new ArrayList<>();
}

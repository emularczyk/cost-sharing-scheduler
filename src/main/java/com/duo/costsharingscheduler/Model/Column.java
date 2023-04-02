package com.duo.costsharingscheduler.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "columns")
@Getter
@Setter
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DynamicInsert
public class Column {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
}

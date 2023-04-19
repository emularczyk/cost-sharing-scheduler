package com.duo.costsharingscheduler.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;

import java.math.BigDecimal;

@Entity
@Table(name = "valueFields")
@Getter
@Setter
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DynamicInsert
public class ValueField {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal valueField = BigDecimal.ZERO;
}

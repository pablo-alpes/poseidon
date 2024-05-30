package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.Instant;


@Entity
@Table(name = "curvepoint")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurvePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int curveId;

    private Instant asOfDate;

    private Double term;

    private Double valueNumber;

    private Instant creationDate;

    public CurvePoint(int curveId, Double term, Double valueNumber) {
        this.term = term;
        this.valueNumber = valueNumber;
        this.curveId = curveId;
    }
}

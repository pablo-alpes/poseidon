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
@Table(name = "CURVEPOINT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurvePoint {
    @Id
    @Column(name = "ID", columnDefinition = "TINYINT(0, 0) not null")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "CURVEID", columnDefinition = "TINYINT(0, 0)")
    private int curveId;

    @Column(name = "ASOFDATE")
    private Instant asofdate;

    @Column(name = "TERM")
    private Double term;

    @Column(name = "VALUENUMBER")
    private Double valueNumber;

    @Column(name = "CREATIONDATE")
    private Instant creationdate;


    public CurvePoint(int curveId, Double term, Double valueNumber) {
        this.term = term;
        this.valueNumber = valueNumber;
        this.curveId = curveId;
    }
}

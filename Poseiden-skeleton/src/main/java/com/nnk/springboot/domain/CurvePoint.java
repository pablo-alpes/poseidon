package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.sql.Timestamp;
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

    @Column(name = "ASOFDATE")
    private Instant asofdate;

    @Column(name = "TERM")
    private Double term;

    @Column(name = "VALUENUMBER")
    private Double valuenumber;

    @Column(name = "CREATIONDATE")
    private Instant creationdate;

    @Column(name = "CURVEID", columnDefinition = "TINYINT(0, 0)")
    private int curveId;

    public CurvePoint(int curveId, Double term, Double valuenumber) {
        this.term = term;
        this.valuenumber = valuenumber;
        this.curveId = curveId;
    }
}

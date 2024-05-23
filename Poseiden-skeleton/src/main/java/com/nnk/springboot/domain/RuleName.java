package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "RULENAME")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RuleName {
    @Id
    @Column(name = "ID", columnDefinition = "TINYINT(0, 0) not null")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Size(max = 125)
    @Column(name = "NAME", length = 125)
    private String name;

    @Size(max = 125)
    @Column(name = "DESCRIPTION", length = 125)
    private String description;

    @Size(max = 125)
    @Column(name = "JSON", length = 125)
    private String json;

    @Size(max = 512)
    @Column(name = "TEMPLATE", length = 512)
    private String template;

    @Size(max = 125)
    @Column(name = "SQLSTR", length = 125)
    private String sql;

    @Size(max = 125)
    @Column(name = "SQLPART", length = 125)
    private String sqlPart;

    public RuleName(String name, String description, String json, String template, String sql, String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sql = sql;
        this.sqlPart = sqlPart;
    }
}

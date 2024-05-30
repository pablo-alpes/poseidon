package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "rulename")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RuleName {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String description;

    private String json;

    private String template;

    private String sqlstr;

    private String sqlpart;

    public RuleName(String name, String description, String json, String template, String sqlstr, String sqlpart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlstr = sqlstr;
        this.sqlpart = sqlpart;
    }
}

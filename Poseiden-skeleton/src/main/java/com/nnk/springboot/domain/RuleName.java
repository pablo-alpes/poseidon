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

    @NotBlank(message = "Field is mandatory")
    private String name;

    @NotBlank(message = "Field is mandatory")
    private String description;

    @NotBlank(message = "Field is mandatory")
    private String json;

    @NotBlank(message = "Field is mandatory")
    private String template;

    @NotBlank(message = "Field is mandatory")
    private String sqlstr;

    @NotBlank(message = "Field is mandatory")
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

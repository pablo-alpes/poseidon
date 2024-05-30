package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;


@Entity
@Table(name = "trade")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tradeId;

    @NotBlank(message = "Field is mandatory")
    private String account;

    @NotBlank(message = "Field is mandatory")
    private String type;

    @NotNull(message = "Field is mandatory")
    private Double buyQuantity;

    private Double sellQuantity;

    private Double buyPrice;

    private Double sellPrice;

    private Instant tradeDate;

    private String security;

    private String status;

    private String trader;

    private String benchmark;

    private String book;

    private String creationName;

    private Instant creationDate;

    private String revisionName;

    private Instant revisionDate;

    private String dealName;

    private String dealType;

    private String sourceListId;

    private String side;

    public Trade(String account, String type) {
        this.account = account;
        this.type = type;
    }
}

package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "bidlist")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BidList {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String account;

    @NotNull
    private String type;

    private Double bidQuantity;

    private Double askQuantity;

    private Double bid;

    private Double ask;

    private String benchmark;

    private Instant bidListDate;

    private String commentary;

    private String security;

    private String status;

    private String trader;

    private String book;

    private String creationName;

    private Instant creationDate;

    private String revisionName;

    private Instant revisionDate;

    private String dealName;

    private String dealType;

    private String sourceListId;

    private String side;

    public BidList(String account, String type, double bidQuantity) {;
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

}

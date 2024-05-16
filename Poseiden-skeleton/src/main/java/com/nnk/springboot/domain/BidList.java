package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "BIDLIST")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BidList {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String account;

    @Size(max = 30)
    @NotNull
    @Column(name = "TYPE", nullable = false, length = 30)
    private String type;

    @Column(name = "BIDQUANTITY")
    private Double bidQuantity;

    @Column(name = "ASKQUANTITY")
    private Double askQuantity;

    @Column(name = "BID")
    private Double bid;

    @Column(name = "ASK")
    private Double ask;

    @Size(max = 125)
    @Column(name = "BENCHMARK", length = 125)
    private String benchmark;

    @Column(name = "BIDLISTDATE")
    private Instant bidlistdate;

    @Size(max = 125)
    @Column(name = "COMMENTARY", length = 125)
    private String commentary;

    @Size(max = 125)
    @Column(name = "SECURITY", length = 125)
    private String security;

    @Size(max = 10)
    @Column(name = "STATUS", length = 10)
    private String status;

    @Size(max = 125)
    @Column(name = "TRADER", length = 125)
    private String trader;

    @Size(max = 125)
    @Column(name = "BOOK", length = 125)
    private String book;

    @Size(max = 125)
    @Column(name = "CREATIONNAME", length = 125)
    private String creationname;

    @Column(name = "CREATIONDATE")
    private Instant creationdate;

    @Size(max = 125)
    @Column(name = "REVISIONNAME", length = 125)
    private String revisionname;

    @Column(name = "REVISIONDATE")
    private Instant revisiondate;

    @Size(max = 125)
    @Column(name = "DEALNAME", length = 125)
    private String dealname;

    @Size(max = 125)
    @Column(name = "DEALTYPE", length = 125)
    private String dealtype;

    @Size(max = 125)
    @Column(name = "SOURCELISTID", length = 125)
    private String sourcelistid;

    @Size(max = 125)
    @Column(name = "SIDE", length = 125)
    private String side;

    public BidList(String account, String type, double bidQuantity) {;
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

}

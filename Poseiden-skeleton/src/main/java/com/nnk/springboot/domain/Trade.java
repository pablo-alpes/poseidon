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
@Table(name = "TRADE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trade {
    @Id
    @Column(name = "TRADEID", columnDefinition = "TINYINT(0, 0) not null")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Size(max = 30)
    @NotNull
    @Column(name = "ACCOUNT", nullable = false, length = 30)
    private String account;

    @Size(max = 30)
    @NotNull
    @Column(name = "TYPE", nullable = false, length = 30)
    private String type;

    @Column(name = "BUYQUANTITY")
    private Double buyquantity;

    @Column(name = "SELLQUANTITY")
    private Double sellquantity;

    @Column(name = "BUYPRICE")
    private Double buyprice;

    @Column(name = "SELLPRICE")
    private Double sellprice;

    @Column(name = "TRADEDATE")
    private Instant tradedate;

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
    @Column(name = "BENCHMARK", length = 125)
    private String benchmark;

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

    public Trade(String account, String type) {
        this.account = account;
        this.type = type;
    }
}

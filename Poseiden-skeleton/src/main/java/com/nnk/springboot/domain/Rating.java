package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "rating")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String moodysRating;

    private String spRating;

    private String fitchRating;

    private int orderNumber;

    public Rating(String moodysRating, String spRating, String fitchRating, int orderNumber) {
        this.moodysRating = moodysRating;
        this.spRating = spRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }
}

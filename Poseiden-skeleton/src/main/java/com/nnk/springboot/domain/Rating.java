package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "RATING")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    @Id
    @Column(name = "ID", columnDefinition = "TINYINT(0, 0) not null")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Size(max = 125)
    @Column(name = "MOODYSRATING", length = 125)
    private String moodysRating;

    @Size(max = 125)
    @Column(name = "SANDPRATING", length = 125)
    private String sandPRating;

    @Size(max = 125)
    @Column(name = "FITCHRATING", length = 125)
    private String fitchRating;

    @Column(name = "ORDERNUMBER", columnDefinition = "TINYINT(0, 0)")
    private int orderNumber;

    public Rating(String moodysRating, String sandPRating, String fitchRating, int orderNumber) {
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }
}

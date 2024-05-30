package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(name="username")
    @NotBlank(message = "Field is mandatory")
    private String username;

    @NotBlank(message = "Field is mandatory")
    @Column(name="password")
    private String password;

    @NotBlank(message = "Field is mandatory")
    @Column(name="full_name")
    private String fullName;

    @NotBlank(message = "Field is mandatory")
    @Column(name="role")
    private String role;

    public User(String username, String password, String fullName, String role) {
        this    .username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

}

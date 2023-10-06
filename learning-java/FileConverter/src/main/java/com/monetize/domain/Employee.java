package com.monetize.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Employee {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String gender;
    private String currency;
    private double salary;

}

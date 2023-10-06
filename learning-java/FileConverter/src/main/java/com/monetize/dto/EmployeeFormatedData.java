package com.monetize.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeFormatedData {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String gender;
    private String currency;
    private String salary;
    private String currencyName;
}

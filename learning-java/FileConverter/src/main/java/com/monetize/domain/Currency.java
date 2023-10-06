package com.monetize.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
    private String symbol;
    private String name;
    private String symbol_native;
    private int decimal_digits;
    private double rounding;
    private String code;
    private String name_plural;
}

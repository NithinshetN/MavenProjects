package com.monetize.iplstats.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class PlayerCountryStatsDto {
  private String country;
  private int totalPlayers;
  private int totalAmount;

  public String toString(){
    return "Country:"+country+"  Total Amount:"+totalAmount+"  Players"+totalPlayers+"\n";
  }
}

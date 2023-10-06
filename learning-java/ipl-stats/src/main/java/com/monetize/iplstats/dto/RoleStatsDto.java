package com.monetize.iplstats.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleStatsDto {
  private String roleName;
  private long totalAmount;
  private long playerCount;

  public String toString(){
    return "Role:"+roleName+"  Total Amount:"+totalAmount+"  Players"+playerCount+"\n";
  }
}

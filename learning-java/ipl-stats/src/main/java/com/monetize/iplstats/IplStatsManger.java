package com.monetize.iplstats;

import com.monetize.iplstats.domain.Player;
import com.monetize.iplstats.dto.PlayerCountryStatsDto;
import com.monetize.iplstats.dto.RoleStatsDto;
import com.monetize.iplstats.service.IplStatService;
import com.monetize.iplstats.service.IplStatServiceImpl;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IplStatsManger {

  public static void main(String[] args) {

    IplStatService statService = new IplStatServiceImpl();
    Scanner sc = new Scanner(System.in);
    while(true){
      System.out.println("""
              1. Get Team Names
              2. Get Players of a Team
              3. Get Team Stats
              4. Get Max Paid Players of Each Team
              5. Get Player Country Stats
              6. Get Top N Paid Players
              7. Get Max Paid Player of Each Role
              8. Get Role Stats
              9. Exit
              """);
      System.out.println("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice){
        case 1:
          System.out.println(statService.getTeamNames());
          break;
        case 2:
          System.out.println("Enter Team Name: ");
          String team=sc.next();
          System.out.println(statService.getPlayers(team.toUpperCase()).toString());
          break;
        case 3:
          System.out.println(statService.getTeamStats());
          break;
        case 4:
          System.out.println(statService.getMaxPaidPlayersOfEachTeam());
          break;
        case 5:
          System.out.println(statService.getPlayerCountryStats().toString());
          break;
        case 6:
          System.out.println("Enter N: ");
          int n = sc.nextInt();
          System.out.println(statService.getTopNPaidPlayers(n).toString());
          break;
        case 7:
          System.out.println(statService.getMaxPaidPlayerOfEachRole());
          break;
        case 8:
          System.out.println(statService.getRoleStats().toString());
          break;
        case 9:
          sc.close();
          System.exit(0);
        default:
          System.out.println("Invalid Choice");
      }
      System.out.println("Do you want to continue y/n ");
      sc.skip("\n");
      if(sc.nextLine().equals("n") ){
          System.exit(0);
      }
    }

  }
}

package com.monetize.iplstats.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.monetize.iplstats.domain.Player;
import com.monetize.iplstats.dto.PlayerCountryStatsDto;
import com.monetize.iplstats.dto.RoleStatsDto;
import com.monetize.iplstats.dto.TeamStatsDto;
import com.monetize.iplstats.util.JsonReaderUtil;

import java.util.*;
import java.util.stream.Collectors;

public class IplStatServiceImpl implements IplStatService{

  private List<Player> players;
  public IplStatServiceImpl(){
      players = JsonReaderUtil.loadFromDataFromJsonFile("/player_data.json", new TypeReference<List<Player>>() {});
  }
  @Override
  public List<String> getTeamNames() {
      return players.stream()
                    .map(Player::getTeam)
                    .distinct()
                    .toList();
  }

  @Override
  public List<Player> getPlayers(String team) {
    return players.stream()
            .filter(player -> Objects.equals(player.getTeam(), team))
            .collect(Collectors.toList());
  }

  @Override
  public List<TeamStatsDto> getTeamStats() {
    Map<String, List<Player>> map = players.stream().collect(
        Collectors.groupingBy(Player::getTeam));
    List<TeamStatsDto> teamStatsDtos = new ArrayList<>();
    for (Map.Entry<String, List<Player>> entry : map.entrySet()) {
      double totalAmount = entry.getValue().stream().mapToDouble(player -> Double.parseDouble(player.getAmount())).sum();
      teamStatsDtos.add(TeamStatsDto.builder().team(entry.getKey()).totalAmount(totalAmount).build());
    }
    return teamStatsDtos;
  }

  @Override
  public List<Map<String, List<Player>>> getMaxPaidPlayersOfEachTeam() {
//    List<MaxTeam> play= new ArrayList<>();
//    for(Player pl:players){
//
//    }
    return null;
  }

  @Override
  public List<PlayerCountryStatsDto> getPlayerCountryStats() {
    List<PlayerCountryStatsDto> countryStats = new ArrayList<>();
    Map<String, List<Player>> playersByCountry = players.stream()
            .collect(Collectors.groupingBy(Player::getCountry));

    for (Map.Entry<String, List<Player>> entry : playersByCountry.entrySet()) {
      String country = entry.getKey();
      List<Player> countryPlayers = entry.getValue();


      int totalPlayers = countryPlayers.size();


      int totalAmount = countryPlayers.stream()
              .mapToInt(player -> Integer.parseInt(player.getAmount()))
              .sum();

      PlayerCountryStatsDto countryStat = PlayerCountryStatsDto.builder()
              .country(country)
              .totalPlayers(totalPlayers)
              .totalAmount(totalAmount)
              .build();


      countryStats.add(countryStat);
    }
    return countryStats;
  }

  @Override
  public List<Player> getTopNPaidPlayers(int n) {
    List<Player> sortPlayers = players.stream()
            .sorted(Comparator.comparingDouble(player -> -Double.parseDouble(player.getAmount())))
            .toList();

      return sortPlayers.stream()
            .limit(n)
            .collect(Collectors.toList());
  }

  @Override
  public List<Map<String, List<Player>>> getMaxPaidPlayerOfEachRole() {
    return null;
  }

  @Override
  public List<RoleStatsDto> getRoleStats() {
    List<RoleStatsDto> roleStatsd = new ArrayList<>();
    Map<String, List<Player>> playersByRole = players.stream()
            .collect(Collectors.groupingBy(Player::getRole));

    for (Map.Entry<String, List<Player>> entry : playersByRole.entrySet()) {
      String role = entry.getKey();
      List<Player> RolePlayers = entry.getValue();


      int totalPlayers = RolePlayers.size();


      long totalAmount = RolePlayers.stream()
              .mapToLong(player -> Long.parseLong(player.getAmount()))
              .sum();

      RoleStatsDto roleStats = RoleStatsDto.builder()
              .roleName(role)
              .playerCount(totalPlayers)
              .totalAmount(totalAmount)
              .build();


      roleStatsd.add(roleStats);
    }
    return roleStatsd;
  }
}

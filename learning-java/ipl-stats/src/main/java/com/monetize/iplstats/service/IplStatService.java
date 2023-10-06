package com.monetize.iplstats.service;

import com.monetize.iplstats.domain.Player;
import com.monetize.iplstats.dto.PlayerCountryStatsDto;
import com.monetize.iplstats.dto.RoleStatsDto;
import com.monetize.iplstats.dto.TeamStatsDto;

import java.util.List;
import java.util.Map;

public interface IplStatService {

    List<String> getTeamNames();
    List<Player> getPlayers(String team);
    List<TeamStatsDto> getTeamStats();
    List<Map<String,List<Player>>> getMaxPaidPlayersOfEachTeam();
    List<PlayerCountryStatsDto> getPlayerCountryStats();
    List<Player> getTopNPaidPlayers(int n);
    List<Map<String,List<Player>>> getMaxPaidPlayerOfEachRole();
    List<RoleStatsDto> getRoleStats();


}

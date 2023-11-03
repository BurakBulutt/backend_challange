package com.example.example_6.dto;

import com.example.example_6.entity.Team;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Dto nesnelerini list seklinde donduren sinifdir.
 */
@Data
public class TeamDtoList {
    private List<TeamDto> teamDtoList;

    public TeamDtoList(List<Team> teamList) {
        this.teamDtoList = teamList.stream()
                .map(Team::toDto)
                .collect(Collectors.toList());
        getTeamDtoList();
    }
}

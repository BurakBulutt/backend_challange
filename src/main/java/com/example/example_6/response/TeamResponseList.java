package com.example.example_6.response;

import com.example.example_6.dto.TeamDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Response nesnelerini liste seklinde dondurur.
 */
@Data
public class TeamResponseList {
    private List<TeamResponse> teamResponses;
    public TeamResponseList(List<TeamDto> teamDtoList) {
        this.teamResponses = teamDtoList
                .stream()
                .map(TeamResponse::toResponse)
                .collect(Collectors.toList());
        getTeamResponses();
    }
}

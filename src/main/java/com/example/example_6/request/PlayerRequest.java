package com.example.example_6.request;

import com.example.example_6.dto.PlayerDto;
import com.example.example_6.entity.Team;
import lombok.Data;

@Data
public class PlayerRequest {
    private String fullName;
    private String position;
    private String teamId;

    public PlayerDto toDto(){
        if (teamId == null){
            return PlayerDto.builder()
                    .fullName(fullName)
                    .position(position)
                    .build();
        }
        return PlayerDto.builder()
                .fullName(fullName)
                .position(position)
                .team(Team.builder().id(teamId).build())
                .build();
    }
}

package com.example.example_6.dto;

import com.example.example_6.entity.Player;
import com.example.example_6.entity.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

/**
 * Player sinifinin dto objesini temsil eder.
 */
@Data
@Builder
public class PlayerDto {
    private Long id;
    private String fullName;
    private String position;
    @JsonIgnore
    private Team team;

    /**
     * Dto objesini Entity objesine cevirir.
     * @return
     */
    public Player toEntity(){
        return Player.builder()
                .fullName(fullName)
                .team(team)
                .position(position)
                .build();
    }

}

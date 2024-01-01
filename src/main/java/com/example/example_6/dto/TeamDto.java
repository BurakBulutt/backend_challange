package com.example.example_6.dto;

import com.example.example_6.entity.Player;
import com.example.example_6.entity.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Team sinifinin dto objesini temsil eder.
 */
@Data
@Builder
public class TeamDto implements Serializable {
    private String id;
    private String name;
    private String country;
    @JsonIgnore
    private List<Player> playerList;

    /**
     * Dto objesini Entity objesine cevirir.
     * @return
     */
    public Team toEntity(){
        return Team.builder()
                .id(id)
                .name(name)
                .country(country)
                .playerList(playerList)
                .build();
    }
}

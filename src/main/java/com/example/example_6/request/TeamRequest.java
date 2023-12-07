package com.example.example_6.request;

import com.example.example_6.dto.TeamDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

/**
 * Team'a ait request nesnesi.
 */
@Data
public class TeamRequest {
    private String id;
    private String name;
    private String country;

    /**
     * Requesti dtoya dönüştürür.
     * @return PlayerDto
     */
    public TeamDto toDto(){
        return TeamDto.builder()
                .id(id)
                .name(name)
                .country(country)
                .playerList(new ArrayList<>())
                .checkedAt(new Date())
                .build();
    }
}


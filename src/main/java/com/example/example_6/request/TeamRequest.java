package com.example.example_6.request;

import com.example.example_6.dto.TeamDto;
import lombok.Data;

import java.util.ArrayList;

@Data
public class TeamRequest {
    private String id;
    private String name;
    private String country;

    public TeamDto toDto(){
        return TeamDto.builder()
                .id(id)
                .name(name)
                .country(country)
                .playerList(new ArrayList<>())
                .build();
    }
}

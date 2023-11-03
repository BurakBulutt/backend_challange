package com.example.example_6.service;

import com.example.example_6.dto.TeamDto;
import com.example.example_6.dto.TeamDtoList;
import com.example.example_6.entity.Team;

/**
 * Team nesnesinin service arayuzu.
 */
public interface TeamService {
    TeamDtoList findAll();
    TeamDto findById(String id);
    TeamDto save(TeamDto dto) throws Exception;
    TeamDto update(String id,TeamDto dto);
    Void delete(String id);

    Team getById(String id);
}

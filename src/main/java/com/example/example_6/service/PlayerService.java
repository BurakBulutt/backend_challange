package com.example.example_6.service;

import com.example.example_6.dto.PlayerDto;
import com.example.example_6.dto.PlayerDtoList;

/**
 * Player nesnesinin service arayuzu.
 */
public interface PlayerService {
    PlayerDtoList findAll();
    PlayerDto findById(Long id);
    PlayerDtoList findPlayersByTeamId(String teamId);
    PlayerDto save(PlayerDto dto);
    PlayerDto update(Long id,PlayerDto dto);
    Void delete(Long id);
}

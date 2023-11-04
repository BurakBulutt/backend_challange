package com.example.example_6.dto;

import com.example.example_6.entity.Player;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Dto nesnelerini list seklinde donduren sinifdir.
 */
@Data
public class PlayerDtoList {
    private List<PlayerDto> playerDtoList;

    public PlayerDtoList(List<Player> playerList) {
        this.playerDtoList = playerList.stream()
                .map(Player::toDto)
                .collect(Collectors.toList());
        getPlayerDtoList();
    }
}






package com.example.example_6.response;

import com.example.example_6.dto.PlayerDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Response nesnelerini liste seklinde dondurur.
 */
@Data
public class PlayerResponseList {
    private List<PlayerResponse> playerResponseList;

    public PlayerResponseList(List<PlayerDto> playerDtoList) {
        this.playerResponseList = playerDtoList
                .stream()
                .map(PlayerResponse::toResponse)
                .collect(Collectors.toList());
        getPlayerResponseList();
    }
}

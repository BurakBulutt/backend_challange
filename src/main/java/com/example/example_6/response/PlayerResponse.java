package com.example.example_6.response;

import com.example.example_6.dto.PlayerDto;
import com.example.example_6.dto.TeamDto;
import lombok.Builder;
import lombok.Data;

/**
 * Player sinifina ait response nesnesi.
 */
@Data
@Builder
public class PlayerResponse {
    private Long id;
    private String fullName;
    private TeamDto team;
    private String position;

    /**
     * Gelen dto nesnesini response nesnesine donusturur.
     * @param dto
     * @return
     */
    public static PlayerResponse toResponse(PlayerDto dto){
        return PlayerResponse.builder()
                .id(dto.getId())
                .fullName(dto.getFullName())
                .team(dto.getTeam().toDto())
                .position(dto.getPosition())
                .build();
    }

}

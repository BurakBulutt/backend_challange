package com.example.example_6.response;

import com.example.example_6.dto.PlayerDto;
import com.example.example_6.dto.PlayerDtoList;
import com.example.example_6.dto.TeamDto;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Team sinifina ait response nesnesi.
 */
@Data
@Builder
public class TeamResponse {
    private String id;
    private String name;
    private String country;
    private List<PlayerDto> playerList;
    private Date checkedAt;

    /**
     * Gelen dto nesnesini response nesnesine donusturur.
     * @param dto
     * @return
     */
    public static TeamResponse toResponse(TeamDto dto){
        return TeamResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .country(dto.getCountry())
                .playerList(new PlayerDtoList(dto.getPlayerList()).getPlayerDtoList())
                .checkedAt(dto.getCheckedAt())
                .build();
    }
}

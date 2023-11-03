package com.example.example_6.entity;

import com.example.example_6.dto.TeamDto;
import com.example.example_6.dto.TeamDtoList;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Bu team tablosuna karsilik gelen entity sinifidir.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = Team.TABLE)
public class Team {
    /**
     * Tablo Adi    */
    public static final String TABLE = "team";
    /**
     * Id sutununun adi     */
    public static final String COL_ID = "id";
    /**
     * Name sutununun adi     */
    public static final String COL_NAME = "name";
    /**
     * Country sutununun adi
     */
    public static final String COL_COUNTRY = "country";

    /**
     * Id sutunu.
     */
    @Id
    @Column(name = Team.COL_ID,length = 3)
    private String id;
    /**
     * Isim sutunu.
     */
    @Column(name = Team.COL_NAME,nullable = false)
    private String name;
    /**
     * Ulke sutunu.
     */
    @Column(name = Team.COL_COUNTRY,nullable = false)
    private String country;
    /**
     * Takima ait oyuncularin listesi bulunur.
     */
    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Player> playerList;

    /**
     * Takim nesnelerini TakimDto nesnelerine donusturur.
     * @return
     */
    public TeamDto toDto() {
        return TeamDto.builder()
                .id(id)
                .name(name)
                .country(country)
                .playerList(playerList)
                .build();
    }
}

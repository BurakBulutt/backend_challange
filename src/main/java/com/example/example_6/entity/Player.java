package com.example.example_6.entity;

import com.example.example_6.dto.PlayerDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

/**
 * Bu player tablosuna karsilik gelen entity sinifidir.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = Player.TABLE)
public class Player {
    /**
     * Tablo adi.
     */
    public static final String TABLE = "player";
    /**
     * Id sutununun adi
     */
    public static final String COL_ID = "id";
    /**
     * fullName sutununun adi
     */
    public static final String COL_FULL_NAME = "full_name";
    /**
     * team_id sutununun adi
     */
    public static final String COL_TEAM_ID = "team_id";
    /**
     * position sutununun adi
     */
    public static final String COL_POSITION = "position";

    /**
     * Id sutunu.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Player.COL_ID)
    private Long id;
    /**
     * Isim sutunu. Oyuncunun isim ve soyisimi tek degisken icerisinde bulunmaktadir.
     */
    @Column(name = Player.COL_FULL_NAME, nullable = false)
    private String fullName;
    /**
     * Takim sutunu. Oyuncunun ait oldugu takimin yabancil anahtarini temsil eder.
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = COL_TEAM_ID, nullable = false)
    @JsonBackReference
    private Team team;
    /**
     * Mevki Sutunu. Oyuncunun oynadigi mevkiyi saklar.
     */
    @Column(name = Player.COL_POSITION, nullable = false)
    private String position;

    /**
     * Player nesnelerini PlayerDto nesnelerine dönüstürür
     * @return
     */
    public PlayerDto toDto() {
        return PlayerDto.builder()
                .id(id)
                .fullName(fullName)
                .team(team)
                .position(position)
                .build();
    }
}

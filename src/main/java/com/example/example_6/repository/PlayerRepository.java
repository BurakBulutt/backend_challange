package com.example.example_6.repository;

import com.example.example_6.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Player sinifinin data access katmani.(repository interfacesi)
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {
    /**
     *
     * @param teamId String tipinde takimin id degerini alir
     * @return takimin idsine ait oyunculari list tipinde dondurur.
     */
    List<Player> findPlayersByTeamId(String teamId);
}

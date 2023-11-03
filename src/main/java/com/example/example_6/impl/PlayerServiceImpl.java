package com.example.example_6.impl;

import com.example.example_6.dto.PlayerDto;
import com.example.example_6.dto.PlayerDtoList;
import com.example.example_6.entity.Player;
import com.example.example_6.entity.Team;
import com.example.example_6.exception.NotFoundException;
import com.example.example_6.repository.PlayerRepository;
import com.example.example_6.service.PlayerService;
import com.example.example_6.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * PlayerService implamantasyonu
 */
@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository repository;
    private final TeamService teamService;

    /**
     * Tum oyunculari iceren listeyi dtoList seklinde dondurur.
     * @return
     */
    @Override
    public PlayerDtoList findAll() {
        return new PlayerDtoList(repository.findAll());
    }

    /**
     * Girilen id'ye gore oyuncuyu dto seklinde dondurur.
     * @param id Istenen oyuncunun bulunmasi icin gerekli id alani.
     * @return
     */
    @Override
    public PlayerDto findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Oyuncu Bulunamadi")).toDto();
    }

    /**
     * Takimlarda oynayan oyunculari dtolist seklinde dondurur.
     * @param teamId Oyunculari takimlarina gore filtrelemek icin gerekli takim id alani.
     * @return
     */
    @Override
    public PlayerDtoList findPlayersByTeamId(String teamId) {
        return new PlayerDtoList(repository.findPlayersByTeamId(teamId));
    }

    /**
     * Once gonderilen oyuncu nesnesinin icerisindeki teamId ye ait bir takim olup olmadigi kontrol edilir.
     * Gonderilen oyuncuyu veritabanina kaydeder daha sonra entityi dtoya dondurup return eder.
     * @param dto kaydedilecek oyuncunun verilerini tutar.
     * @return
     */
    @Override
    public PlayerDto save(PlayerDto dto) {
        Team team = teamService.getById(dto.getTeam().getId());
        dto.setTeam(team);
        return repository.save(dto.toEntity()).toDto();
    }

    /**
     * Girilen id'ye gore oyuncuyu bulup dto nesnesinde doldurulan alanlar icin guncelleme islemini yapar.
     * @param id Kaydedilecek oyuncunun bulunması icin gerekli id alani.
     * @param dto Guncellenmesi istenen alanlarin bilgilerini tutan nesne.
     * @return
     */
    @Override
    public PlayerDto update(Long id, PlayerDto dto) {
        Optional<Player> updatedPlayer = repository.findById(id);
        if (updatedPlayer.isPresent()) {
            Player player = updatedPlayer.get().builder()
                    .id(updatedPlayer.get().getId())
                    .fullName(dto.getFullName() != null ? dto.getFullName() : updatedPlayer.get().getFullName())
                    .position(dto.getPosition() != null ? dto.getPosition() : updatedPlayer.get().getPosition())
                    .team(dto.getTeam() != null ? teamService.getById(dto.getTeam().getId()) : updatedPlayer.get().getTeam())
                    .build();
            return repository.save(player).toDto();
        }
        throw new NotFoundException("Guncellencek Kullanici Bulunamadi ");
    }

    /**
     * Gonderilen id numarasina ait takimi siler.
     * @param id Silinecek takimin bulunmasi icin gerekli id alani.
     * @return
     */
    @Override
    public Void delete(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return null;
        }
        throw new NotFoundException("Silinecek Kullanici Bulunamadi");
    }
}

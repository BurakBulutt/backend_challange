package com.example.example_6.impl;

import com.example.example_6.dto.TeamDto;
import com.example.example_6.dto.TeamDtoList;
import com.example.example_6.entity.Information;
import com.example.example_6.entity.Team;
import com.example.example_6.exception.NotFoundException;
import com.example.example_6.repository.InformationRepository;
import com.example.example_6.repository.TeamRepository;
import com.example.example_6.service.TeamService;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * TeamService implamantasyonu
 */
@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository repository;

    @Resource(name = "hazelCastInstance")
    private final HazelcastInstance hazelcastInstance;
    @Resource(name = "rabbitTemplate")
    private final RabbitTemplate rabbitTemplate;

    /**
     * Tum takimlari iceren listeyi dtoList seklinde dondurur.
     * @return
     */
    @Override
    public TeamDtoList findAll() {
        return new TeamDtoList(repository.findAll());
    }

    /**
     * Girilen id'ye gore takimi dto seklinde dondurur.
     * @param id Istenen takimin bulunmasi icin gerekli id alani.
     * @return
     */
    @Override
    public TeamDto findById(String id) {
        IMap<String,TeamDto> teamCache = hazelcastInstance.getMap("teamCache");
        TeamDto cacheTeam = teamCache.get(id);

        if (cacheTeam != null){
            return cacheTeam;
        }
        TeamDto team = repository.findById(id).orElseThrow(()-> new NotFoundException("Takim Bulunamadi")).toDto();
        teamCache.put(team.getId(),team);
        rabbitTemplate.convertAndSend(team);
        return team;
    }

    /**
     * Gonderilen Takimi veritabanina kaydeder daha sonra entityi dtoya dondurup return eder.
     * @param dto kaydedilecek takimin verilerini tutar.
     * @return
     */
    @Override
    public TeamDto save(TeamDto dto) throws Exception {
        if (repository.findById(dto.getId()).isPresent()){
            throw new Exception("Bu idye ait takim bulunuyor.");
        }
        return repository.save(dto.toEntity()).toDto();
    }

    /**
     * Girilen id'ye gore takimi bulup dto nesnesinde doldurulan alanlar icin guncelleme islemini yapar.
     * @param id Kaydedilecek Takimin bulunması icin gerekli id alani.
     * @param dto Guncellenmesi istenen alanlarin bilgilerini tutan nesne.
     * @return
     */
    @Override
    public TeamDto update(String id, TeamDto dto) {
        Optional<Team> updatedTeam = repository.findById(id);
        if (updatedTeam.isPresent()){
            Team team = updatedTeam.get().builder()
                    .id(updatedTeam.get().getId())
                    .name(dto.getName()!=null ? dto.getName() : updatedTeam.get().getName())
                    .country(dto.getCountry()!=null ? dto.getCountry() : updatedTeam.get().getCountry())
                    .playerList(updatedTeam.get().getPlayerList())
                    .build();
            return repository.save(team).toDto();
        }
        throw new NotFoundException("Guncellenecek Takim Bulunamadi");
    }

    /**
     * Gonderilen id numarasina ait takimi siler.
     * @param id Silinecek takimin bulunmasi icin gerekli id alani.
     * @return
     */
    @Override
    public Void delete(String id) {
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            return null;
        }
        throw new NotFoundException("Silinecek Takim Bulunamadi");
    }

    /**
     * Gonderilen id ye gore takimi getirir.
     * @param id Getirilecek takimi bulmak icin gerekli id alani.
     * @return
     */
    @Override
    public Team getById(String id) {
        return repository.findById(id).orElseThrow(()-> new NotFoundException("Takim Bulunamadi"));
    }
}

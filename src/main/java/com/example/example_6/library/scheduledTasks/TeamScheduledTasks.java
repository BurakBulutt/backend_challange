package com.example.example_6.library.scheduledTasks;

import com.example.example_6.entity.Team;
import com.example.example_6.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class TeamScheduledTasks {
    private final TeamRepository repository;

    @Scheduled(cron = "0 0 12 * * ?")
    void updateCheckedAt(){
        for (Team team : repository.findAll()){
            team.setCheckedAt(new Date());
            repository.save(team);
        }
    }

}

package com.example.example_6.library.config;

import com.example.example_6.dto.TeamDto;
import com.example.example_6.entity.Information;
import com.example.example_6.repository.InformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InformationReceiver {
    private final InformationRepository repository;

    @RabbitListener(queues = "informationQueue")
    public void receiveInformationMessage(TeamDto team){
        Information info = new Information();
        info.setLogMessage(team.getId() + " idye sahip " + team.getName() + " takım ön belleğe eklendi.");
        repository.save(info);
    }
}

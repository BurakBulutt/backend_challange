package com.example.example_6.library.scheduledTasks.TeamBatch;

import com.example.example_6.entity.Team;
import com.example.example_6.repository.TeamRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class TeamBatchConfig {
    private final TeamRepository teamRepository;
    @Autowired
    public JobBuilder jobBuilder;
    @Autowired
    public StepBuilder stepBuilder;

    @Bean
    public Step step1(){
        return stepBuilder.tasklet(((contribution, chunkContext) -> {
            for (Team team : teamRepository.findAll()){
                team.setCheckedAt(new Date());
                teamRepository.save(team);
            }
            return RepeatStatus.FINISHED;
        }))
                .build();
    }

    @Bean
    public Job UpdateCheckedAtJob(){
        return jobBuilder.flow(step1())
                .end()
                .build();
    }


}

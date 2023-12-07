package com.example.example_6;

import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Example6Application {

    public static void main(String[] args) {
        SpringApplication.run(Example6Application.class, args);
    }

    @Bean
    public JobBuilder getInstance(){
        return new JobBuilder("getJob");
    }

    @Bean
    public StepBuilder getInstanceStep(){
        return new StepBuilder("getStep");
    }

}

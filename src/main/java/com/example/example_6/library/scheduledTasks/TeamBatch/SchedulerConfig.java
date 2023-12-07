package com.example.example_6.library.scheduledTasks.TeamBatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job updatedCheckedAtJob;

    @Scheduled(cron = "0 0 * * 13 ?")
    void runUpdateCheckedAtJob() throws Exception {
        JobParameters parameters = new JobParametersBuilder()
                .addString("JobID",String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(updatedCheckedAtJob,parameters);
    }
}

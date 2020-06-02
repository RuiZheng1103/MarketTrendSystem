package com.rui.trend.config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rui.trend.job.IndexDataSyncJob;

@Configuration
public class QuartzConfiguration {

	// interval time setting
	private static final int interval = 240;
	
    @Bean
    public JobDetail weatherDataSyncJobDetail() {
        return JobBuilder.newJob(IndexDataSyncJob.class)
        				.withIdentity("indexDataSyncJob")
        				.storeDurably()
        				.build();
    }
     
    @Bean
    public Trigger weatherDataSyncTrigger() {
        SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule()
                												.withIntervalInMinutes(interval)
                												.repeatForever();
         
        return TriggerBuilder.newTrigger()
			        		.forJob(weatherDataSyncJobDetail())
			                .withIdentity("indexDataSyncTrigger")
			                .withSchedule(schedBuilder).build();
    }
}
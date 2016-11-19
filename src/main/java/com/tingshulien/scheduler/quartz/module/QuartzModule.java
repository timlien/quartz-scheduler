package com.tingshulien.scheduler.quartz.module;

import com.google.inject.AbstractModule;

import com.tingshulien.scheduler.quartz.job.JobFactoryImpl;
import com.tingshulien.scheduler.quartz.service.BackupService;
import com.tingshulien.scheduler.quartz.service.BackupServiceImpl;
import com.tingshulien.scheduler.quartz.service.RewardService;
import com.tingshulien.scheduler.quartz.service.RewardServiceImpl;

import org.quartz.spi.JobFactory;

public class QuartzModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(JobFactory.class)
                .to(JobFactoryImpl.class);

        bind(BackupService.class)
                .to(BackupServiceImpl.class);

        bind(RewardService.class)
                .to(RewardServiceImpl.class);
    }

}

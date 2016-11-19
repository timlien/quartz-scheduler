package com.tingshulien.scheduler.quartz.job;

import com.google.inject.Inject;

import com.tingshulien.scheduler.quartz.service.BackupService;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackupJob implements Job {

    private static Logger LOGGER = LoggerFactory.getLogger(BackupJob.class);

    @Inject
    private BackupService service;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        service.backup();

        LOGGER.info("Backup Arena Rank Record for Fugu Servers");
    }

}

package com.tingshulien.scheduler.quartz.job;

import com.google.inject.Inject;

import com.tingshulien.scheduler.quartz.service.RewardService;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerRewardJob implements Job {

    private static Logger LOGGER = LoggerFactory.getLogger(ServerRewardJob.class);

    @Inject private RewardService service;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        int serverId = context.getMergedJobDataMap().getInt("serverId");

        service.reward();

        LOGGER.info("Execute Arena Daily Reward for Fugu Server " + serverId);
    }
}

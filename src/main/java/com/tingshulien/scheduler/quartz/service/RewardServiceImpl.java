package com.tingshulien.scheduler.quartz.service;

import com.google.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class RewardServiceImpl implements RewardService {

    private static Logger LOGGER = LoggerFactory.getLogger(RewardServiceImpl.class);

    @Override
    public void reward() {
        LOGGER.info("Execute reward service");
    }
}

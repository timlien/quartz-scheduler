package com.tingshulien.scheduler.quartz.service;

import com.google.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class BackupServiceImpl implements BackupService {

    private static Logger LOGGER = LoggerFactory.getLogger(BackupServiceImpl.class);

    @Override
    public void backup() {
        LOGGER.info("Execute backup service");
    }
}

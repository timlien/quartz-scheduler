package com.tingshulien.scheduler.quartz.job;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class JobFactoryImpl implements JobFactory {

    private static Logger LOGGER = LoggerFactory.getLogger(JobFactoryImpl.class);

    private final Injector guice;

    @Inject
    public JobFactoryImpl(final Injector guice) {
        this.guice = guice;
    }

    @Override
    public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException {

        JobDetail jobDetail = bundle.getJobDetail();
        Class jobClass = jobDetail.getJobClass();

        try {
            // Get a new instance of that class from Guice so we can do dependency injection
            return (Job) guice.getInstance(jobClass);
        } catch (Exception ex) {
            // Something went wrong.  Print out the stack trace here so SLF4J doesn't hide it.
            LOGGER.error("Guice failed to inject dependencies into the job " + jobClass.getName(), ex);

            // Rethrow the exception as an UnsupportedOperationException
            throw new UnsupportedOperationException(ex);
        }
    }

}

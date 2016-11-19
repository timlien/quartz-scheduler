package com.tingshulien.scheduler.quartz;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import com.tingshulien.scheduler.quartz.module.QuartzModule;

import org.quartz.Scheduler;
import org.quartz.SchedulerMetaData;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.JobFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Inject private JobFactory jobFactory;

    public App() {
        inject(this);
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }

    private void inject(App app) {
        Injector guice = Guice.createInjector(new QuartzModule());
        guice.injectMembers(app);
    }

    private void run() throws Exception {
        // make a reference to a scheduler
        Scheduler scheduler;

        try {
            // create schedule with quartz.properties
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            // add custom guice injected job factory
            scheduler.setJobFactory(jobFactory);
        } catch (NoClassDefFoundError e) {
            LOGGER.error(" Unable to load a class - most likely you do not have jta.jar on the classpath. If not present in the examples/lib folder, please " +
                    "add it there for this sample to run.", e);
            return;
        }

        LOGGER.info("------- Initialization Complete -----------");
        LOGGER.info("------- (Not Scheduling any Jobs - relying on XML definitions --");
        LOGGER.info("------- Starting Scheduler ----------------");

        // start the schedule
        scheduler.start();

        LOGGER.info("------- Started Scheduler -----------------");
        LOGGER.info("------- Waiting five minutes... -----------");

        // wait five minutes to give our jobs a chance to run
        try {
            Thread.sleep(300L * 1000L);
        } catch (Exception e) {
            //
        }

        // shut down the scheduler
        LOGGER.info("------- Shutting Down ---------------------");
        scheduler.shutdown(true);
        LOGGER.info("------- Shutdown Complete -----------------");

        SchedulerMetaData metaData = scheduler.getMetaData();
        LOGGER.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }
}

package nl.dulsoft.iot.mqtt.schedule;

import nl.dulsoft.iot.mqtt.service.MqttService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.core.jmx.JobDataMapSupport.newJobDataMap;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
@Startup
@Singleton
public class ScheduleCoordinator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleCoordinator.class);

    public static final String SONOFF1 = "sonoff1";
    public static final String SWITCH_ON = "switchOn";
    public static final String SWITCH_OFF = "switchOff";

    private MqttService mqttService;

    private Scheduler scheduler;

    @Inject
    public void setMqttService(MqttService mqttService) {
        this.mqttService = mqttService;
    }

    @PostConstruct
    public void startUp() throws SchedulerException {
        LOGGER.info("Starting the job scheduler");
        scheduler = getScheduler();

        scheduler.scheduleJob(
            createJob(SWITCH_ON, SONOFF1, SwitchJob.ON),
            createTrigger(SWITCH_ON, SONOFF1, "0 0 20 ? * * *")
        );

        scheduler.scheduleJob(
            createJob(SWITCH_OFF, SONOFF1, SwitchJob.OFF),
            createTrigger(SWITCH_OFF, SONOFF1, "0 0 23 ? * MON,TUE,WED,THU,SUN *")
        );

        scheduler.scheduleJob(
            createJob(SWITCH_OFF, SONOFF1, SwitchJob.OFF),
            createTrigger(SWITCH_OFF, SONOFF1, "0 30 23 ? * FRI,SAT *")
        );

        LOGGER.info("Starting the scheduler");
        scheduler.start();
    }

    @PreDestroy
    public void cleanUp() throws SchedulerException {
        LOGGER.info("Shutdown the scheduler, waiting for jobs to complete");
        getScheduler().shutdown(true);
        LOGGER.info("Scheduler is shut down");
    }

    private JobDetail createJob(String name, String group, String state) {
        LOGGER.info(String.format("Create job %s.%s to %s", name, group, state));
        Map<String, Object> dataMap = Collections.unmodifiableMap(
            Stream.of(
                new AbstractMap.SimpleEntry<>(SwitchJob.DEVICE, group),
                new AbstractMap.SimpleEntry<>("state", state),
                new AbstractMap.SimpleEntry<>("service", mqttService)
            ).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()))
        );
        return newJob(SwitchJob.class)
            .withIdentity(name, group)
            .usingJobData(newJobDataMap(dataMap))
            .build();
    }

    private Trigger createTrigger(String name, String group, String schedule) {
        LOGGER.info(String.format("Create trigger %s.%s to %s", name, group, schedule));
        return newTrigger()
            .withIdentity(name, group)
            .withSchedule(cronSchedule(schedule))
            .build();
    }

    Scheduler getScheduler() throws SchedulerException {
        return new StdSchedulerFactory().getScheduler();
    }
}

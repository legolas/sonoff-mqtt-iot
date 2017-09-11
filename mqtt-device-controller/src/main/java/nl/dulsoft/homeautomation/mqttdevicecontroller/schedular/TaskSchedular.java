package nl.dulsoft.homeautomation.mqttdevicecontroller.schedular;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
@Component
public class TaskSchedular {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskSchedular.class);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    void reportCurrentTime() {
        LOGGER.info("Current time is {}", DATE_FORMAT.format(new Date()));
        // Check to see if the configuration file has changed
        // if that's the case replace all registered tasks with the new ones
    }

}

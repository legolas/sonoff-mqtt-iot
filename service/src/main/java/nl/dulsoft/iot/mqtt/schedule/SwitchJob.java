package nl.dulsoft.iot.mqtt.schedule;

import nl.dulsoft.iot.mqtt.paho.PahoClient;
import nl.dulsoft.iot.mqtt.service.MqttService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Date;

/**
 * Job definition for
 *
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class SwitchJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(SwitchJob.class);

    // TODO Provide as part of the JobExecutionContext
    private MqttService mqttService;

    static final String DEVICE = "device";
    static final String STATE = "state";

    @Inject
    public void setMqttService(MqttService mqttService) {
        this.mqttService = mqttService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String device = getDevice(jobExecutionContext);
        String state = getMap(jobExecutionContext);

        updateState(device, state);
        LOGGER.info(String.format("%s - Turn %s %s!", new Date().toString(), device, state));
    }

    private void updateState(String device, String state) throws JobExecutionException {
        if ("on".equalsIgnoreCase(state)) {
            mqttService.switchOn(device);
        } else if ("off".equalsIgnoreCase(state)) {
            mqttService.switchOff(device);
        } else {
            throw new JobExecutionException(String.format("Invalid state (%s) provided.", state));
        }
    }

    private String getMap(JobExecutionContext jobExecutionContext) {
        return jobExecutionContext
                .getJobDetail()
                .getJobDataMap()
                .get(STATE)
                .toString();
    }

    private String getDevice(JobExecutionContext jobExecutionContext) {
        return jobExecutionContext
                .getJobDetail()
                .getJobDataMap()
                .get(DEVICE).toString();
    }
}

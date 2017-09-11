package nl.dulsoft.homeautomation.mqttdevicecontroller.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class ScheduleTest {

    public static final String CRON = "0 0 0 0 0 0 0";
    public static final String ACTION = "on";

    @Test
    public void itShouldReturnTheSetValues() {
        Schedule schedule = new Schedule();
        schedule.setAction(ACTION)
            .setCron(CRON);

        assertEquals(ACTION, schedule.getAction());
        assertEquals(CRON, schedule.getCron());
    }
}
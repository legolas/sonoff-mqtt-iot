package nl.dulsoft.homeautomation.mqttdevicecontroller.model;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class DeviceTest {

    @Test
    public void itShouldReturnTheSetValues() {
        Device device = new Device()
            .setId("sonoff01")
            .setLabel("Boekenkast verlichting")
            .setLocation(new Location())
            .addSchedule(new Schedule())
            .addSchedule(new Schedule())
            .addSchedule(new Schedule());

        assertEquals("sonoff01", device.getId());
        assertEquals("Boekenkast verlichting", device.getLabel());
        assertNotNull(device.getLocation());
        assertEquals(3, device.getListOfSchedule().size());
    }

    @Test
    public void itShoulsSetScheduleList() {
        assertNotNull(new Device().setListOfSchedule(Collections.emptyList()));
    }
}
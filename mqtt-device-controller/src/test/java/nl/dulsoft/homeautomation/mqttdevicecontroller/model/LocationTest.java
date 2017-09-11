package nl.dulsoft.homeautomation.mqttdevicecontroller.model;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class LocationTest {

    @Test
    public void itShouldReturnTheSetValues() {
        Location location = new Location()
            .setName("name")
            .addDevices(new Device())
            .addDevices(new Device())
            .addDevices(new Device());

        assertEquals("name", location.getName());
        assertEquals(3, location.getDevices().size());
    }

    @Test
    public void itShoulsSetDeviceList() {
        assertNotNull(new Location().setDevices(Collections.emptyList()));
    }
}
package nl.dulsoft.iot.mqtt.service;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A {@link Device} object describes a device, its state and its schedules.
 *
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class DeviceTest {

    public static final String DEVICE_ID = "sonoff666";

    @Test
    public void itShouldReturn_DeviceID() {
        Device device = new Device();
        device.setId(DEVICE_ID);
        assertEquals(DEVICE_ID, device.getId());
    }

    @Test
    public void itShouldReturn_DeviceState_On() {
        Device device = new Device();
        device.setState(MqttItemState.ON);
        assertEquals(MqttItemState.ON, device.getState());
    }
}
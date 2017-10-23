package nl.dulsoft.homeautomation.io;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class DeviceConfigurationErrorTest {

    @Test
    public void itShouldBeCreated() {
        assertNotNull(new DeviceConfigurationError(null, null));
    }

//    @Test
//    public void itShouldHaveMessageAndCause() {
//
//        assertNotNull(new DeviceConfigurationError(null, null));
//    }
}
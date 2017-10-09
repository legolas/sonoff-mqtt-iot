package nl.dulsoft.iot.mqtt.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class MessageExceptionTest {

    private static final String ERROR_MESSAGE = "error message";

    @Test
    public void shouldCreate_WithMessage() {
        assertEquals(ERROR_MESSAGE, new MessageException(ERROR_MESSAGE).getMessage());
    }

    @Test
    public void shouldCreate_WithMessageAndThrowable() {
        assertEquals(ERROR_MESSAGE, new MessageException(ERROR_MESSAGE).getMessage());
    }
}
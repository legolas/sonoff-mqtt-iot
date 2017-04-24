package nl.dulsoft.iot.mqtt.service;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class MessageException extends RuntimeException {

    public MessageException(String msg) {
        super(msg);
    }
}

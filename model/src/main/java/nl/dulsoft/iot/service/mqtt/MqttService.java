package nl.dulsoft.iot.service.mqtt;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public interface MqttService {

    /**
     * Determins the state for the given MQTT item
     *
     * @param itemId defines the item requested
     * @return ON or OFF
     */
    MqttItemState getState(String itemId);
}

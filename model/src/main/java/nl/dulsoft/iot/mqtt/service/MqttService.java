package nl.dulsoft.iot.mqtt.service;

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

    /**
     * Toggles the state of the switch
     *
     * @param itemId
     */
    void toggleState(String itemId);

    /**
     * Set's the state of the switch on
     *
     * @param itemId
     */
    void switchOn(String itemId);

    /**
     * Set's the state of the switch off
     *
     * @param itemId
     */
    void switchOff(String itemId);

    void setState(String itemId, MqttItemState state);
}

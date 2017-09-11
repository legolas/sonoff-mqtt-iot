package nl.dulsoft.iot.mqtt.service;

import nl.dulsoft.iot.mqtt.paho.PahoClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
@Default
public class MqttServiceImpl implements MqttService {

    private static final String PUBLISH_FMT = "cmnd/%s/POWER";
    private static final String TOGGLE = "toggle";
    private static final String ON = "ON";
    private static final String OFF = "OFF";

    private PahoClient client;

    @Inject
    public MqttServiceImpl(PahoClient client) {
        this.client = client;
    }

    /**
     * Retrieves the state of the item identified by the provided itemId
     *
     * @param itemId defines the item requested
     * @return
     * @throws MessageException when sending has failed
     */
    @Override
    public MqttItemState getState(String itemId) {
        return MqttItemState.ON;
    }

    /**
     * Toggles the state of the switch
     *
     * @param itemId defines the item to control
     * @throws MessageException when sending has failed
     */
    public void toggleState(String itemId) {

        try {
            client.send(String.format(PUBLISH_FMT, itemId), TOGGLE);
        } catch (MqttException me) {
            throw new MessageException(String.format("Failed to toggle the state of %s", itemId), me);
        }
    }

    /**
     * Set's the state of the switch on
     *
     * @param itemId
     * @throws MessageException when sending has failed
     */
    public void switchOn(String itemId) {
        try {
            client.send(String.format(PUBLISH_FMT, itemId), ON);
        } catch (MqttException me) {
            throw new MessageException(String.format("Failed to toggle the state of %s", itemId), me);
        }
    }

    /**
     * Set's the state of the switch off
     *
     * @param itemId
     * @throws MessageException when sending has failed
     */
    public void switchOff(String itemId) {
        try {
            client.send(String.format(PUBLISH_FMT, itemId), OFF);
        } catch (MqttException me) {
            throw new MessageException(String.format("Failed to toggle the state of %s", itemId), me);
        }
    }

    @Override
    public void setState(String itemId, MqttItemState state) {
        switch (state) {
            case ON:
                switchOn(itemId);
                break;
            case OFF:
                switchOff(itemId);
                break;
        }
    }
}

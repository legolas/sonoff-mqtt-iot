package nl.dulsoft.iot.mqtt.service;

import nl.dulsoft.iot.mqtt.paho.PahoClient;

import javax.inject.Inject;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
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
     */
    @Override
    public MqttItemState getState(String itemId) {
        return MqttItemState.ON;
    }

    /**
     * Toggles the state of the switch
     *
     * @param itemId
     */
    public void toggleState(String itemId) {
        client.send(String.format(PUBLISH_FMT, itemId), TOGGLE);
    }

    /**
     * Set's the state of the switch on
     *
     * @param itemId
     */
    public void switchOn(String itemId) {
        client.send(String.format(PUBLISH_FMT, itemId), ON);
    }

    /**
     * Set's the state of the switch off
     *
     * @param itemId
     */
    public void switchOff(String itemId) {
        client.send(String.format(PUBLISH_FMT, itemId), OFF);
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

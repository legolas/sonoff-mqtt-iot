package nl.dulsoft.iot.mqtt.service;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class Device {
    private String id;
    private MqttItemState state;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public MqttItemState getState() {
        return state;
    }

    public void setState(MqttItemState state) {
        this.state = state;
    }
}

package nl.dulsoft.iot.mqtt.paho;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import javax.enterprise.inject.Default;
import java.util.Optional;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
@Default
public class PahoClient {

    private String BROKER = "tcp://192.168.1.150";
    private MqttClient mqttClient;

    public void send(String topic, String message) {

    }

    MqttClient getMqttClient() throws MqttException {
        return Optional
            .ofNullable(this.mqttClient)
            .orElse(new MqttClient(null, null));
    }

    void setMqttClient(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }
}

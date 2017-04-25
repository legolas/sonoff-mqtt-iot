package nl.dulsoft.iot.mqtt.paho;

import nl.dulsoft.iot.mqtt.service.MessageException;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Default;
import java.util.Optional;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
@Default
public class PahoClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(PahoClient.class);

    private static final String BROKER = "tcp://192.168.1.150";
    private static final String CLIENT_ID = "Mqtt Client";
    private MqttClient mqttClient;

    public void send(String topic, String message) throws MqttException {
        LOGGER.info("send({}, {})", topic, message);
        getMqttClient().publish(topic, message.getBytes(), 2, false);
    }

    MqttClient getMqttClient() throws MqttException {
        if (!Optional.ofNullable(this.mqttClient)
            .isPresent()) {
            setMqttClient(createClient());
        }

        if (!this.mqttClient.isConnected()) {
            LOGGER.info("Connect MQTT client");
            this.mqttClient.connect(createConnectionOptions());
        }

        return this.mqttClient;
    }

    void setMqttClient(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    MqttClient createClient() {
        LOGGER.info("Create MQTT client");
        try {
            return new MqttClient(BROKER, CLIENT_ID, new MemoryPersistence());
        } catch (MqttException e) {
            throw new MessageException(
                String.format("Cannot create client %s for %s",
                    CLIENT_ID, BROKER));
        }
    }

    private MqttConnectOptions createConnectionOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);

        return options;
    }
}

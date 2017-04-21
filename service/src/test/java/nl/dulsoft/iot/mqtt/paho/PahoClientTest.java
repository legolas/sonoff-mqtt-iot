package nl.dulsoft.iot.mqtt.paho;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class PahoClientTest {

    private static final String SEND_TOPIC = "cmnd/sonoff1/POWER";
    private static final String ON_MESSAGE = "ON";

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private MqttClient mqttClient;

    @Captor
    private ArgumentCaptor<MqttMessage> messageArgument;
    @InjectMocks
    private PahoClient pahoClient;

    @Test
    @Ignore
    public void shouldSend_ON_To_sonoff1() throws MqttException {
        when(mqttClient.isConnected()).thenReturn(true);

        pahoClient.send(SEND_TOPIC, ON_MESSAGE);
        
        verify(mqttClient).publish(SEND_TOPIC, messageArgument.capture());
        assertEquals(ON_MESSAGE.getBytes(), messageArgument.getValue().getPayload());

    }
}
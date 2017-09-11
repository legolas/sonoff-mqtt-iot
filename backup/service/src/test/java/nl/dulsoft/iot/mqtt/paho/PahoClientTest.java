package nl.dulsoft.iot.mqtt.paho;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class PahoClientTest {

    private static final String SEND_TOPIC = "cmnd/sonoff1/POWER";
    private static final String ON_MESSAGE = "ON";
    private static final String OFF_MESSAGE = "OFF";

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private MqttClient mqttClient;

    @Captor
    private ArgumentCaptor<byte[]> messageArgument;
    @Captor
    private ArgumentCaptor<String> topicArgument;

    @InjectMocks
    private PahoClient pahoClient;

    @Test
    public void shouldSend_ON_To_sonoff1() throws MqttException {
        when(mqttClient.isConnected()).thenReturn(true);

        pahoClient.send(SEND_TOPIC, ON_MESSAGE);

        verify(mqttClient).publish(topicArgument.capture(), messageArgument.capture(), eq(2), eq(false));

        assertArrayEquals(ON_MESSAGE.getBytes(), messageArgument.getValue());
        assertEquals(SEND_TOPIC, topicArgument.getValue());
    }

    @Test
    public void shouldSend_OFF_To_sonoff1() throws MqttException {
        when(mqttClient.isConnected()).thenReturn(true);

        pahoClient.send(SEND_TOPIC, OFF_MESSAGE);

        verify(mqttClient).publish(topicArgument.capture(), messageArgument.capture(), eq(2), eq(false));

        assertArrayEquals(OFF_MESSAGE.getBytes(), messageArgument.getValue());
        assertEquals(SEND_TOPIC, topicArgument.getValue());
    }

    @Test
    public void shouldConnect_BeforeSending_OFF_To_sonoff1() throws MqttException {
        when(mqttClient.isConnected()).thenReturn(false);

        pahoClient.send(SEND_TOPIC, OFF_MESSAGE);

        verify(mqttClient).connect(any(MqttConnectOptions.class));
    }

    @Test
    public void shouldCreateClientAndConnect_BeforeSending_ON_To_sonoff1() throws MqttException {

        // Spy the creation of a new MqttClient
        PahoClient spyClient = spy(pahoClient);
        spyClient.setMqttClient(null);
        doReturn(mqttClient).when(spyClient).createClient();

        spyClient.send(SEND_TOPIC, ON_MESSAGE);

        verify(mqttClient).connect(any(MqttConnectOptions.class));
        verify(mqttClient).publish(topicArgument.capture(), messageArgument.capture(), eq(2), eq(false));

        assertEquals(SEND_TOPIC, topicArgument.getValue());
        assertArrayEquals(ON_MESSAGE.getBytes(), messageArgument.getValue());
    }

    @Test
    public void shouldCreateMqttClient() {
        MqttClient client = pahoClient.createClient();
        assertNotNull(client);
    }

    @Test
    public void shouldFailToCreateMqttClient() {
        assertNotNull(pahoClient.createClient());
    }
}
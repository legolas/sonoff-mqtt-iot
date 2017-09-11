package nl.dulsoft.iot.mqtt.service;

import nl.dulsoft.iot.mqtt.paho.PahoClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class MqttServiceImplTest {

    private static final String ITEM_ID = "sonoff1";
    private static final String TOPIC = "cmnd/sonoff1/POWER";
    private static final String OFF = "OFF";
    private static final String ON = "ON";
    private static final String TOGGLE = "toggle";

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private PahoClient pahoClient;

    @InjectMocks
    private MqttServiceImpl mqttService;

    @Test
    public void toggleStateShouldCallPahoClient_WithTopic_AndMsgToggle() throws MqttException {
        mqttService.toggleState(ITEM_ID);
        verify(pahoClient).send(TOPIC, "toggle");
    }

    @Test(expected = MessageException.class)
    public void toggleStateShouldThrowMessageException() throws MqttException {
        doThrow(new MqttException(1)).when(pahoClient).send(TOPIC, TOGGLE);
        mqttService.toggleState(ITEM_ID);
    }

    @Test
    public void switchOnShouldCallPahoClient_WithTopic_AndMsgOn() throws MqttException {
        mqttService.switchOn(ITEM_ID);
        verify(pahoClient).send(TOPIC, ON);
    }

    @Test(expected = MessageException.class)
    public void switchOnShouldThrowMessageException() throws MqttException {
        doThrow(new MqttException(1)).when(pahoClient).send(TOPIC, ON);
        mqttService.switchOn(ITEM_ID);
    }

    @Test
    public void switchOffShouldCallPahoClient_WithTopic_AndMsgOff() throws MqttException {
        mqttService.switchOff(ITEM_ID);
        verify(pahoClient).send(TOPIC, OFF);
    }

    @Test(expected = MessageException.class)
    public void switchOffShouldThrowMessageException() throws MqttException {
        doThrow(new MqttException(1)).when(pahoClient).send(TOPIC, OFF);
        mqttService.switchOff(ITEM_ID);
    }

    @Test
    public void setStatehouldCallPahoClient_WithTopic_AndMsgOff() throws MqttException {
        mqttService.setState(ITEM_ID, MqttItemState.OFF);
        verify(pahoClient).send(TOPIC, OFF);
    }

    @Test
    public void setStatehouldCallPahoClient_WithTopic_AndMsgOn() throws MqttException {
        mqttService.setState(ITEM_ID, MqttItemState.ON);
        verify(pahoClient).send(TOPIC, ON);
    }
}
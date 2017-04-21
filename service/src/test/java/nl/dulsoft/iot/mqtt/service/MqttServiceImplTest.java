package nl.dulsoft.iot.mqtt.service;

import nl.dulsoft.iot.mqtt.paho.PahoClient;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class MqttServiceImplTest {

    private static final String ITEM_ID = "sonoff1";
    private static final String TOPIC = "cmnd/sonoff1/POWER";

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private PahoClient pahoClient;

    @InjectMocks
    private MqttServiceImpl mqttService;

    @Test
    public void shouldReturn_On_ForITEM_ID() throws Exception {
        assertEquals(MqttItemState.ON, mqttService.getState(ITEM_ID));
    }

    @Ignore
    @Test
    public void shouldReturn_Off_ForITEM_ID() throws Exception {
        assertEquals(MqttItemState.OFF, mqttService.getState(ITEM_ID));
    }

    @Test
    public void toggleStateShouldCallPahoClient_WithTopic_AndMsgToggle() {
        mqttService.toggleState(ITEM_ID);
        verify(pahoClient).send(TOPIC, "toggle");
    }

    @Test
    public void switchOnShouldCallPahoClient_WithTopic_AndMsgOn() {
        mqttService.switchOn(ITEM_ID);
        verify(pahoClient).send(TOPIC, "ON");
    }

    @Test
    public void switchOffShouldCallPahoClient_WithTopic_AndMsgOff() {
        mqttService.switchOff(ITEM_ID);
        verify(pahoClient).send(TOPIC, "OFF");
    }

    @Test
    public void setStatehouldCallPahoClient_WithTopic_AndMsgOff() {
        mqttService.setState(ITEM_ID, MqttItemState.OFF);
        verify(pahoClient).send(TOPIC, "OFF");
    }

    @Test
    public void setStatehouldCallPahoClient_WithTopic_AndMsgOn() {
        mqttService.setState(ITEM_ID, MqttItemState.ON);
        verify(pahoClient).send(TOPIC, "ON");
    }
}
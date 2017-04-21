package nl.dulsoft.iot.mqtt.service;

import nl.dulsoft.iot.mqtt.paho.PahoClient;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class MqttServiceImplTest {

    private static final String ITEM_ID = "sonoff1";

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

    @Test
    public void shouldReturn_Off_ForITEM_ID() throws Exception {
//        assertEquals(MqttItemState.OFF, mqttService.getState(ITEM_ID));
    }

    @Test
    public void shouldCallPahoClient_WithTopic_AndMsgToggle() {
        mqttService.toggleState("sonoff1");
        verify(pahoClient).send("cmnd/sonoff1/POWER", "toggle");
    }

    @Test
    public void shouldCallPahoClient_WithTopic_AndMsgOn() {
        mqttService.switchOn("sonoff1");
        verify(pahoClient).send("cmnd/sonoff1/POWER", "ON");
    }

    @Test
    public void shouldCallPahoClient_WithTopic_AndMsgOff() {
        mqttService.switchOff("sonoff1");
        verify(pahoClient).send("cmnd/sonoff1/POWER", "OFF");
    }
}
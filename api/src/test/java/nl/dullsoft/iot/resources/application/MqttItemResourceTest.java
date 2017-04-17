package nl.dullsoft.iot.resources.application;

import nl.dulsoft.iot.service.mqtt.MqttItemState;
import nl.dulsoft.iot.service.mqtt.MqttService;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class MqttItemResourceTest {

    private static final String ITEM_ID = "sonoff1";
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private MqttService mqttService;

    @InjectMocks
    private MqttItemResource resource;

    @Test
    public void shouldReturnOnState_ForSonoffSwitch_IdentifiedByPathParam() {
        when(mqttService.getState(ITEM_ID)).thenReturn(MqttItemState.ON);
        assertEquals(MqttItemState.ON, resource.getState(ITEM_ID));
    }

    @Test
    public void shouldRetsurnOffState_ForSonoffSwitch_IdentifiedByPathParam() {
        when(mqttService.getState(ITEM_ID)).thenReturn(MqttItemState.OFF);
        assertEquals(MqttItemState.OFF, resource.getState(ITEM_ID));
    }
}
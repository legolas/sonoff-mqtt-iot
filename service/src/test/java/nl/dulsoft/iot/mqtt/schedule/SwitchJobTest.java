package nl.dulsoft.iot.mqtt.schedule;

import nl.dulsoft.iot.mqtt.paho.PahoClient;
import nl.dulsoft.iot.mqtt.service.MqttService;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.Serializable;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class SwitchJobTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private SwitchJob switchJob;

    @Mock
    private MqttService mqttService;

    @Test
    public void shouldSwitchOnSonoff1() throws JobExecutionException {
        JobExecutionContext executionContext = mock(JobExecutionContext.class);
        JobDetail jobDetail = mock(JobDetail.class);

        Map<? extends Serializable, String> map = Collections
            .unmodifiableMap(Stream.of(new SimpleEntry<>(SwitchJob.DEVICE, "sonoff1"),
                new SimpleEntry<>(SwitchJob.STATE, "on"))
            .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));

        when(executionContext.getJobDetail()).thenReturn(jobDetail);
        when(jobDetail.getJobDataMap()).thenReturn(new JobDataMap(map));

        switchJob.execute(executionContext);

        verify(mqttService).switchOn("sonoff1");
    }

    @Test
    public void shouldSwitchOffSonoff1() throws JobExecutionException {
        JobExecutionContext executionContext = mock(JobExecutionContext.class);
        JobDetail jobDetail = mock(JobDetail.class);

        Map<? extends Serializable, String> map = Collections
            .unmodifiableMap(Stream.of(new SimpleEntry<>(SwitchJob.DEVICE, "sonoff1"),
                new SimpleEntry<>(SwitchJob.STATE, "off"))
            .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));

        when(executionContext.getJobDetail()).thenReturn(jobDetail);
        when(jobDetail.getJobDataMap()).thenReturn(new JobDataMap(map));

        switchJob.execute(executionContext);

        verify(mqttService).switchOff("sonoff1");
    }

    @Test(expected = JobExecutionException.class)
    public void shouldThrowException() throws JobExecutionException {
        JobExecutionContext executionContext = mock(JobExecutionContext.class);
        JobDetail jobDetail = mock(JobDetail.class);

        Map<? extends Serializable, String> map = Collections
            .unmodifiableMap(Stream.of(new SimpleEntry<>(SwitchJob.DEVICE, "sonoff1"),
                new SimpleEntry<>(SwitchJob.STATE, "stop"))
            .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));

        when(executionContext.getJobDetail()).thenReturn(jobDetail);
        when(jobDetail.getJobDataMap()).thenReturn(new JobDataMap(map));

        switchJob.execute(executionContext);

        verify(mqttService).switchOff("sonoff1");
    }
}
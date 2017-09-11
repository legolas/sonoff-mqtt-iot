package nl.dulsoft.iot.mqtt.schedule;

import nl.dulsoft.iot.mqtt.service.MqttService;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.quartz.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class ScheduleCoordinatorTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private MqttService mqttService;
    @Mock
    private SchedulerFactory schedulerFactory;
    @Mock
    private Scheduler scheduler;
    @Captor
    private ArgumentCaptor<JobDetail> jobDetailCaptor;
    @Captor
    private ArgumentCaptor<Trigger> triggerCaptor;

    @InjectMocks
    private ScheduleCoordinator scheduleCoordinator;

    @Test
    public void shouldBeCreated() {
        assertNotNull(scheduleCoordinator);
    }

    @Test
    public void shouldScheduleOnAt8PMMSunMonTueWedThuDay() throws SchedulerException {
        ScheduleCoordinator scheduleCoordinatorSpy = spy(scheduleCoordinator);
        when(scheduleCoordinatorSpy.getScheduler()).thenReturn(scheduler);

        scheduleCoordinatorSpy.startUp();

        verify(scheduler, times(3)).scheduleJob(jobDetailCaptor.capture(), triggerCaptor.capture());
        verify(scheduler).start();

        List<JobDetail> jobDetails = jobDetailCaptor.getAllValues();

        assertEquals("switchOn", jobDetails.get(0).getKey().getName());
        assertEquals("sonoff1", jobDetails.get(0).getKey().getGroup());
        assertEquals(SwitchJob.ON, jobDetails.get(0).getJobDataMap().getString("state"));

        assertEquals("switchOff0", jobDetails.get(1).getKey().getName());
        assertEquals("sonoff1", jobDetails.get(1).getKey().getGroup());
        assertEquals(SwitchJob.OFF, jobDetails.get(1).getJobDataMap().getString("state"));

        assertEquals("switchOff1", jobDetails.get(2).getKey().getName());
        assertEquals("sonoff1", jobDetails.get(2).getKey().getGroup());
        assertEquals(SwitchJob.OFF, jobDetails.get(2).getJobDataMap().getString("state"));

        List<Trigger> triggers = triggerCaptor.getAllValues();

        assertEquals(ScheduleCoordinator.SWITCH_ON,triggers.get(0).getKey().getName());
        assertEquals(ScheduleCoordinator.SONOFF1,triggers.get(0).getKey().getGroup());
        assertTrue(triggers.get(0).getScheduleBuilder() instanceof CronScheduleBuilder);

        assertEquals(String.format("%s0", ScheduleCoordinator.SWITCH_OFF),triggers.get(1).getKey().getName());
        assertEquals(ScheduleCoordinator.SONOFF1,triggers.get(1).getKey().getGroup());
        assertTrue(triggers.get(1).getScheduleBuilder() instanceof CronScheduleBuilder);

        assertEquals(String.format("%s1", ScheduleCoordinator.SWITCH_OFF),triggers.get(2).getKey().getName());
        assertEquals(ScheduleCoordinator.SONOFF1,triggers.get(2).getKey().getGroup());
        assertTrue(triggers.get(2).getScheduleBuilder() instanceof CronScheduleBuilder);
    }

    @Test
    public void shouldShutdownSchedular() throws SchedulerException {
        ScheduleCoordinator scheduleCoordinatorSpy = spy(scheduleCoordinator);
        when(scheduleCoordinatorSpy.getScheduler()).thenReturn(scheduler);

        scheduleCoordinatorSpy.cleanUp();

        verify(scheduler).shutdown(true);
    }
}
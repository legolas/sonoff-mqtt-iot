package nl.dulsoft.homeautomation.mqttdevicecontroller.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class Model {

    @Test
    public void createJson() throws IOException {
        Schedule schedule1 = new Schedule().setAction("on").setCron("0 1 2 3 4 5 6");
        Schedule schedule2 = new Schedule().setAction("off").setCron("6 5 4 3 2 1 0");
        Schedule schedule3 = new Schedule().setAction("toggle").setCron("6 5 4 3 2 1 0");

        Device device1 = new Device()
            .setId("sonoff01")
            .setLabel("Boekenkast")
            .setListOfSchedule(Stream.of(schedule1, schedule2, schedule3).collect(Collectors.toList()));

        Device device2 = new Device()
            .setId("sonoff02")
            .setLabel("Televisielamp")
            .setListOfSchedule(Stream.of(schedule1, schedule2, schedule3).collect(Collectors.toList()));

        Location woonkamer = new Location()
            .setDevices(Stream.of(device1, device2).collect(Collectors.toList()))
            .setName("Woonkamer");

        Configuration configuration = new Configuration()
            .addLocation(woonkamer);

        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        objectMapper.writeValue(baos, configuration);
        byte[] bytes = baos.toByteArray();
        String json = new String(bytes);
        System.out.println(json);
    }


}

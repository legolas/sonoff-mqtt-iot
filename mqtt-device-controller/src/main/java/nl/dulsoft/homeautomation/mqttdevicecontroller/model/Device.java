package nl.dulsoft.homeautomation.mqttdevicecontroller.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
@XmlRootElement
public class Device {

    private String id;
    private String label;
    private List<Schedule> listOfSchedule;

    public String getId() {
        return id;
    }

    public Device setId(String id) {
        this.id = id;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public Device setLabel(String label) {
        this.label = label;
        return this;
    }

    public List<Schedule> getListOfSchedule() {
        return listOfSchedule;
    }

    public Device addSchedule(Schedule schedule) {
        this.listOfSchedule = Optional.ofNullable(this.listOfSchedule).orElse(new ArrayList<>());
        this.listOfSchedule.add(schedule);

        return this;
    }

    public Device setListOfSchedule(List<Schedule> listOfSchedule) {
        this.listOfSchedule = listOfSchedule;
        return this;
    }
}

package nl.dulsoft.homeautomation.mqttdevicecontroller.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.ResourceSupport;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location extends ResourceSupport {

    private String name;
    private List<Device> devices;

    public String getName() {
        return name;
    }

    public Location setName(String name) {
        this.name = name;
        return this;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public Location setDevices(List<Device> devices) {
        this.devices = devices;
        return this;
    }

    public Location addDevices(Device device) {
        this.devices = Optional.ofNullable(this.devices).orElse(new ArrayList<>());
        devices.add(device);
        return this;
    }
}

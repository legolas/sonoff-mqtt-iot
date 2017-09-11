package nl.dulsoft.homeautomation.mqttdevicecontroller.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
@XmlRootElement
public class Configuration {

    private List<Location> locations;

    public List<Location> getLocations() {
        return locations;
    }

    public Configuration addLocation(Location location) {
        this.locations = Optional.ofNullable(this.locations).orElse(new ArrayList<>());
        this.locations.add(location);
        return this;
    }

    public Configuration setLocations(List<Location> locations) {
        this.locations = locations;
        return this;
    }
}

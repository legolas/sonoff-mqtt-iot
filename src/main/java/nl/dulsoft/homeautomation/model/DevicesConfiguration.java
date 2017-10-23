package nl.dulsoft.homeautomation.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
@XmlRootElement
public class DevicesConfiguration {

    private List<Location> locations;

    public List<Location> getLocations() {
        return locations;
    }

    public DevicesConfiguration setLocations(List<Location> locations) {
        this.locations = locations;
        return this;
    }

    public DevicesConfiguration addLocation(Location location) {
        this.locations = Optional.ofNullable(this.locations).orElse(new ArrayList<>());
        this.locations.add(location);
        return this;
    }
}

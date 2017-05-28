package nl.dulsoft.iot.mqtt.schedule;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
@Startup
@Singleton
public class Schedular {

    public Schedular() {}
    @PostConstruct
    public void startUp() {

    }

    @PreDestroy
    public void cleanUp() {

    }
}

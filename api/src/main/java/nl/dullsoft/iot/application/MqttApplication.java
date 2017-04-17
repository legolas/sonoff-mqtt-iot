package nl.dullsoft.iot.application;

import nl.dullsoft.iot.resources.application.MqttItemResource;

import javax.ws.rs.core.Application;
import java.util.Set;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class MqttApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();

        addRestResourceClasses(resources);
//        addExceptionMappers(resources);

        // Swagger provides the API documentation
//        addSwagger(resources);

        return resources;
    }

//    private void addExceptionMappers(Set<Class<?>> resources) {
//        resources.add(RuntimeExceptionHandler.class);
//    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(MqttItemResource.class);
    }
}

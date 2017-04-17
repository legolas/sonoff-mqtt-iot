package nl.dullsoft.iot.resources.application;

import nl.dulsoft.iot.service.mqtt.MqttItemState;
import nl.dulsoft.iot.service.mqtt.MqttService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;



/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
@Path(MqttItemResource.API_PATH)
public class MqttItemResource {

    static final String API_PATH = "/api/v1/mqttitem";
    private MqttService mqttService;

    @GET
    @Path("{itemId}")
    public MqttItemState getState(@PathParam("itemId") String itemId) {

        return mqttService.getState(itemId);
    }

    @Inject
    public void setMqttService(MqttService service) {
        this.mqttService = service;
    }
}

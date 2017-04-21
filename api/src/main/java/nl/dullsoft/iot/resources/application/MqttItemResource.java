package nl.dullsoft.iot.resources.application;

import nl.dulsoft.iot.mqtt.service.MqttItemState;
import nl.dulsoft.iot.mqtt.service.MqttService;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
@Path(MqttItemResource.API_PATH)
public class MqttItemResource {

    static final String API_PATH = "/api/v1/devices";
    private MqttService mqttService;

    @GET
    @Path("{itemId}")
    public MqttItemState getState(@NotNull @PathParam("itemId") String itemId) {

        return mqttService.getState(itemId);
    }

    @PUT
    @Path("{itemId}")
    public void setState(@NotNull @PathParam("itemId") String itemId, MqttItemState state) {
        mqttService.setState(itemId, state);
    }

    @Inject
    public void setMqttService(MqttService service) {
        this.mqttService = service;
    }
}

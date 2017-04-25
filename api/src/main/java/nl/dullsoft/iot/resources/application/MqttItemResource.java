package nl.dullsoft.iot.resources.application;

import nl.dulsoft.iot.mqtt.service.MqttItemState;
import nl.dulsoft.iot.mqtt.service.MqttService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(MqttItemResource.class);

    static final String API_PATH = "/api/v1/devices";
    private MqttService mqttService;

    @GET
    @Path("{itemId}")
    public MqttItemState getState(@NotNull @PathParam("itemId") String itemId) {
        LOGGER.info("getState({})", itemId);
        return mqttService.getState(itemId);
    }

    @PUT
    @Path("{itemId}")
    public void setState(@NotNull @PathParam("itemId") String itemId,
                         String state) {
        LOGGER.info("setState({})", itemId, state);

        MqttItemState mqttItemState = MqttItemState.valueOf(state);
        mqttService.setState(itemId, mqttItemState);
    }

    @Inject
    public void setMqttService(MqttService service) {
        this.mqttService = service;
    }
}

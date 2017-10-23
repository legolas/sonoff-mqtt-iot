package nl.dulsoft.homeautomation.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.dulsoft.homeautomation.model.DevicesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
@Component
public class DevicesLoader {

    private static final Logger LOGGER= LoggerFactory.getLogger(DevicesLoader.class);

    private DevicesConfiguration devicesConfiguration;

    public DevicesLoader(@Autowired Path configurationFilePath) {
        devicesConfiguration = new DevicesConfiguration();
        parse(configurationFilePath);
    }

    private void parse(Path configurationFilePath) {
        LOGGER.info("Reading configuration from file {}.", configurationFilePath);
        String json = readFile(configurationFilePath);
        parseJsonString(json);
    }

    private String readFile(Path configurationFilePath) {
        try {
            byte[] bytes = Files.readAllBytes(configurationFilePath);
            return new String (bytes);
        } catch (IOException e) {
            String msg = String.format("Error reading device configuration file %s, bailing out.", configurationFilePath);
            LOGGER.error(msg);
            throw new DeviceConfigurationError(msg, e);
        }
    }

    private void parseJsonString(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            devicesConfiguration = objectMapper.readValue(json, DevicesConfiguration.class);
        } catch (IOException e) {
            String msg = String.format("Error parsing device configuration file, bailing out.");
            LOGGER.error(msg);
            throw new DeviceConfigurationError(msg, e);
        }
    }

    public DevicesConfiguration getDevicesConfiguration() {
        return devicesConfiguration;
    }
}

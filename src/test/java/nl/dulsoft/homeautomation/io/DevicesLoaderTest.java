package nl.dulsoft.homeautomation.io;

import nl.dulsoft.homeautomation.model.DevicesConfiguration;
import nl.dulsoft.homeautomation.model.Location;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class DevicesLoaderTest {

    private Path path;
    private Path workingPath;

    @Before
    public void initialise() {
         Path currentPath = Paths.get(".");
         workingPath = currentPath.toAbsolutePath();
         path = workingPath.resolve("src/test/resources/config/devices.json");
    }

    @Test
    public void itShouldBeCreatedWithPathToDevicesFile() {
        assertNotNull(new DevicesLoader(path));
    }

    @Test
    public void itShouldReturnDeviceConfigurationInstance() {
        DevicesLoader devicesLoaderUnderTest = new DevicesLoader(path);

        assertNotNull(devicesLoaderUnderTest.getDevicesConfiguration());
    }

    @Test
    public void devicesConfigurationShouldHaveOneLocationWithNameWoonkamer() {
        DevicesLoader devicesLoaderUnderTest = new DevicesLoader(path);

        DevicesConfiguration devicesConfiguration = devicesLoaderUnderTest.getDevicesConfiguration();
        List<Location> locations = devicesConfiguration.getLocations();

        assertEquals(1, locations.size());
        assertEquals("Woonkamer", locations.get(0).getName());
    }

    @Test(expected = DeviceConfigurationError.class)
    public void shouldThrowWhenPathDoesNotExist() {
        new DevicesLoader(Paths.get("/not/existing/folder/file.json"));
    }

    @Test(expected = DeviceConfigurationError.class)
    public void shouldThrowForFaultyJson (){
        Path path = workingPath.resolve("src/test/resources/config/faulty.json");
        new DevicesLoader(path);
    }
}

package nl.dulsoft.homeautomation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class SonoffMqttApplicationTests {

    @Autowired
    private SonoffMqttApplication sonoffMqttApplicationUnderTest;

    @Test
    public void configurationFilePathShouldExist() {
        Path path = sonoffMqttApplicationUnderTest.configurationFilePath();
        assertNotNull(path);

    }

    @Test
    public void canReadConfigurationFile() {
        Path path = sonoffMqttApplicationUnderTest.configurationFilePath().toAbsolutePath();
        assertTrue(path.toFile().canRead());
    }
}

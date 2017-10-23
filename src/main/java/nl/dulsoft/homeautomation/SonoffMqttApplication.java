package nl.dulsoft.homeautomation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class SonoffMqttApplication {


    public static void main(String[] args) {
        SpringApplication.run(SonoffMqttApplication.class, args);
    }

    @Autowired
    Environment environment;

    @Bean
    public Path configurationFilePath() {
        String configurationFilePath = environment.getProperty("configuration.file.path");
        return Paths.get(configurationFilePath);
    }
}

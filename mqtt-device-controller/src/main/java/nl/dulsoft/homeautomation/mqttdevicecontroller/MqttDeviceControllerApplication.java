package nl.dulsoft.homeautomation.mqttdevicecontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class MqttDeviceControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqttDeviceControllerApplication.class, args);
    }
}

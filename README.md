# Sonoff MQTT IoT

The aim of this project is to control multiple sonoff devices using MQTT.

The sonoff devices have all been prepped to support the [Sonoff-Tasmota](https://github.com/arendst/Sonoff-Tasmota/wiki) firmware.
This firmware provides MQTT support to the sonoff devices.

## Dependencies

The solution:
* uses the [paho MQTT](https://eclipse.org/paho/clients/java/) java library for MQTT communication;
* relies on a running MQTT broker, like [Mosquitto](http://www.instructables.com/id/Installing-MQTT-BrokerMosquitto-on-Raspberry-Pi/);
* uses [ngx-mqtt](https://www.npmjs.com/package/ngx-mqtt)
* uses [ngx-ui-switch](https://www.npmjs.com/package/ngx-ui-switch)
* the solution is deployed as spring boot standalone application services
* scheduling is realised using the description on [baeldung blog](http://www.baeldung.com/spring-scheduled-tasks) in combination with [this github example](https://github.com/Charlynux/task-scheduling/blob/master/src/main/java/demo/TaskSchedulingApplication.java).

## Deployment


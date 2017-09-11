import { Component } from '@angular/core';
import { MqttItemService } from './devices.service';
import 'rxjs/add/operator/toPromise';

@Component({
    selector: "app-root",
    templateUrl: "./app.component.html",
    styleUrls: ["./app.component.css"]
})
export class AppComponent {
    title = "Home Automation";
    enable: boolean = false;

    constructor(private mqttService: MqttItemService) {
    }

    onChange(newValue) {
        this.enable = newValue;
        console.info("Change " + this.enable);

        if (this.enable) {
            this.switchOn();
        } else {
            this.switchOff();
        }
    }

    switchOn() {
        console.info("#clickOn");
        this.mqttService
            .updateState("ON")
            .subscribe(() => {
                console.log("Send 'ON' to sonoff1");
            });
    }

    // this.mqttService.updateState("OFF");
    switchOff() {
        console.info("#clickOff");
        this.mqttService
            .updateState("OFF")
            .subscribe(() => {
                console.log("Send 'OFF' to sonoff1");
            });
    }
}

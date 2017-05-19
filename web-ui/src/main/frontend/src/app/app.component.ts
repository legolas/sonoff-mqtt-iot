import { Component } from '@angular/core';
import { MqttItemService } from './mqtt-item.service';
import 'rxjs/add/operator/toPromise';

@Component({
    selector: "app-root",
    templateUrl: "./app.component.html",
    styleUrls: ["./app.component.css"]
})
export class AppComponent {
    title = "Home Automation";
    model: boolean = false;

    constructor(private mqttService: MqttItemService) {
    }

    clickOn() {
        console.info("#clickOn");
        this.mqttService
            .updateState("ON")
            .subscribe(() => {
                console.log("Send 'ON' to sonoff1");
            });
    }

    clickOff() {
        // this.mqttService.updateState("OFF");
        console.info("#clickOff");
        this.mqttService
            .updateState("OFF")
            .subscribe(() => {
                console.log("Send 'OFF' to sonoff1");
            });
    }
}

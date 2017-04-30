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
            .toPromise()
            .then(() => {
                console.log("Switched on");
            })
            .catch(e => console.log(e));
    }

    clickOff() {
        // this.mqttService.updateState("OFF");
        console.info("#clickOn");
    }
}

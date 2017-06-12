import { UiSwitchModule } from 'ngx-ui-switch/src';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';

import { MqttItemService } from './devices.service';
import { MqttMessage, MqttModule, MqttService } from 'ngx-mqtt';

export const MQTT_SERVICE_OPTIONS = {
    hostname: '192.168.1.150',
    port: 9001,
    path: '/mqtt'
};

export function mqttServiceFactory() {
    return new MqttService(MQTT_SERVICE_OPTIONS);
}

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        MqttModule.forRoot({
            provide: MqttService,
            useFactory: mqttServiceFactory
        }),
        NgbModule.forRoot(),
        UiSwitchModule
    ],
    providers: [
        MqttItemService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}

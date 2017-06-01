import { UiSwitchModule } from 'ngx-ui-switch/src';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MqttItemService } from './mqtt-item.service';

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
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

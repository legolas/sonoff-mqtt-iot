import { async, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpModule } from '@angular/http';
import { MqttItemService } from './devices.service';
import { UiSwitchModule } from 'ngx-ui-switch';
import { by } from 'protractor';

describe("AppComponent", () => {

    let fixture;
    let app;
    let mqttService;
    let spy;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [
                AppComponent
            ],
            providers: [
                MqttItemService
            ],

            imports: [
                BrowserModule,
                FormsModule,
                HttpModule,
                NgbModule.forRoot(),
                UiSwitchModule
            ]
        }).compileComponents();

        fixture = TestBed.createComponent(AppComponent);
        app = fixture.debugElement.componentInstance;

        mqttService = fixture.debugElement.injector.get(MqttItemService);

        spy = spyOn(mqttService, "updateState");
        expect(spy).toBeDefined();
    }));

    it("should create the app", async(() => {
        expect(app).toBeTruthy();
    }));

    it("should have as title 'Home Automation'", async(() => {
        expect(app.title).toEqual("Home Automation");
    }));

    it("should render title in a h1 tag", async(() => {
        fixture.detectChanges();
        const compiled = fixture.debugElement.nativeElement;
        expect(compiled.querySelector("h1").textContent).toContain("Home Automation");
    }));

    it("should have a button with id 'sonoff1'", async(() => {
        const compiled = fixture.debugElement.nativeElement;
        expect(compiled.querySelector("#sonoff1")).toBeTruthy();
    }));

    it("first click should call MqttItemService.updateState with 'ON', second click with 'OFF'", async(() => {
        fixture.detectChanges();
        const compiled = fixture.debugElement.nativeElement;
        const toggle = compiled.querySelector("#sonoff1");

        toggle.click();
        expect(mqttService.updateState).toHaveBeenCalledWith("ON");

        toggle.click();
        expect(mqttService.updateState).toHaveBeenCalledWith("OFF");
    }));
});

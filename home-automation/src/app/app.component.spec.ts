import { async, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpModule } from '@angular/http';
import { MqttItemService } from './mqtt-item.service';

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
                NgbModule.forRoot()
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

    xit("should call MqttItemService.updateState('ON')", async(() => {
        app.clickOn();
        expect(mqttService.updateState).toHaveBeenCalledWith("ON");
    }));

    xit("should call MqttItemService.updateState('OFF')", async(() => {
        app.clickOff()
            .subscribe((response) =>{
                expect(mqttService.updateState).toHaveBeenCalledWith("OFF");
            });
    }));
});

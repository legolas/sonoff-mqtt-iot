import { inject, TestBed } from '@angular/core/testing';

import { MqttItemService } from './mqtt-item.service';
import { MockBackend, MockConnection } from '@angular/http/testing';
import { BaseRequestOptions, Http, RequestMethod, Response, ResponseOptions } from '@angular/http';

describe("MqttItemService", () => {

    let subject: MqttItemService;
    let backend: MockBackend;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                MockBackend,
                BaseRequestOptions,
                {
                    provide: Http,
                    useFactory: (backendInstance: MockBackend, defaultOptions: BaseRequestOptions) => {
                        return new Http(backendInstance, defaultOptions);
                    },
                    deps: [MockBackend, BaseRequestOptions]
                },
                MqttItemService
            ]
        });
    });

    beforeEach(inject([MqttItemService, MockBackend], (mqttService: MqttItemService, mockBackend: MockBackend) => {
        subject = mqttService;
        backend = mockBackend;
    }));

    it("should be created", inject([MqttItemService], (service: MqttItemService) => {
        expect(service).toBeTruthy();
    }));

    it("should send put request to remote service with value 'ON'", (done => {
        backend.connections.subscribe((connection: MockConnection) => {
            let options = new ResponseOptions({
                body: JSON.stringify({success: true})
            });
            connection.mockRespond(new Response(options));

            // Check the request method
            expect(connection.request.method).toEqual(RequestMethod.Put);
            // Check the url
            expect(connection.request.url).toEqual("/devices/api/v1/sonoff1");
            // Check the body
            expect(connection.request.text()).toEqual("ON");
            // expect(connection.request.text()).toEqual(JSON.stringify({username: "admin", password: "secret"}));
            // Check the request headers
            expect(connection.request.headers.get("Content-Type")).toEqual("application/json");
        });

        subject
            .updateState("ON")
            .subscribe((response) => {
                // Check the response
                expect(response.json()).toEqual({success: true});
                done();
            })

    }));
    it("should send put request to remote service with value 'OFF'", (done => {
        backend.connections.subscribe((connection: MockConnection) => {
            let options = new ResponseOptions({
                body: JSON.stringify({success: true})
            });
            connection.mockRespond(new Response(options));

            // Check the request method
            expect(connection.request.method).toEqual(RequestMethod.Put);
            // Check the url
            expect(connection.request.url).toEqual("/devices/api/v1/sonoff1");
            // Check the body
            expect(connection.request.text()).toEqual("OFF");
            // expect(connection.request.text()).toEqual(JSON.stringify({username: "admin", password: "secret"}));
            // Check the request headers
            expect(connection.request.headers.get("Content-Type")).toEqual("application/json");
        });

        subject
            .updateState("OFF")
            .subscribe((response) => {
                // Check the response
                expect(response.json()).toEqual({success: true});
                done();
            })

    }));
});

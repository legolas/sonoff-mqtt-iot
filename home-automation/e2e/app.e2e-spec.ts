import { HomeAutomationPage } from "./app.po";
import { by, element } from 'protractor';

describe("home-automation App", () => {
    let page: HomeAutomationPage;

    beforeEach(() => {
        page = new HomeAutomationPage();
    });

    it("should display message saying app works", () => {
        page.navigateTo();
        expect(page.getParagraphText()).toEqual("app works!");
    });

    it("should display the 'On' button", () => {
        element(by.buttonText("On")).isDisplayed();
    });

    it("should display the 'Off' button", () => {
        element(by.buttonText("Off")).isDisplayed();
    });
});

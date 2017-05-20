import { HomeAutomationPage } from "./app.po";
import { by, element } from 'protractor';

describe("Home Automation App", () => {
    let page: HomeAutomationPage;

    beforeEach(() => {
        page = new HomeAutomationPage();
    });

    it("should display message saying app works", () => {
        page.navigateTo();
        expect(page.getParagraphText()).toEqual("Home Automation");
    });

    it("should display the 'On' button", () => {
        element(by.id("btn-on")).isDisplayed();
    });

    it("should display the 'Off' button", () => {
        element(by.id("btn-off")).isDisplayed();
    });
});
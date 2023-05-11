package io.github.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertEquals;

public class SaucePage {

    private WebDriver driver;

    @FindBy(id = "user-name")
    @CacheLookup
    private WebElement input_username;

    @FindBy(id = "password")
    @CacheLookup
    private WebElement input_password;

    @FindBy(id = "login-button")
    @CacheLookup
    private WebElement button_login;

    @FindBy(css = ".error h3")
    @CacheLookup
    private WebElement alert_message;

    @FindBy(css = "span.title")
    @CacheLookup
    private WebElement page_header;

    public SaucePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void enterUsername(String username) {
        input_username.sendKeys(username);
    }

    public void enterPassword(String password) {
        input_password.sendKeys(password);
    }

    public void clickLoginButton() {
        button_login.click();
    }

    public WebElement getAlert_message() {
        return alert_message;
    }

    public WebElement getPage_header() {
        return page_header;
    }

    public void verifyText(String actualText, String expectedText) {
        assertEquals(actualText, expectedText);
    }
}

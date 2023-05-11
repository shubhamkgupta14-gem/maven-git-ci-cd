package io.github.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.hooks.TestHook;
import io.github.pageobjects.SaucePage;
import org.openqa.selenium.WebDriver;

import java.util.Base64;

import static org.testng.Assert.assertEquals;

public class SauceTest {

    WebDriver driver = TestHook.driver;
    SaucePage saucePage = new SaucePage(driver);

    @Given("Verifies user is on login page")
    public void verifiesUserIsOnLoginPage() {
        try {
            assertEquals(saucePage.getTitle(), "Swag Labs");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fail to verify title.\n Actual is : "
                    + saucePage.getTitle() + "Expected was : Swag Labs");
        }
    }

    @When("Enters username {string}")
    public void entersUsername(String username) {
        try {
            saucePage.enterUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fail to enter username - " + username);
        }
    }

    @And("Enters password {string}")
    public void entersPassword(String password) {
        try {
            String D_Password = new String(Base64.getDecoder().decode(password));
            saucePage.enterPassword(D_Password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fail to enter password - " + password);
        }
    }

    @And("Clicks on login button")
    public void clicksOnLoginButton() {
        try {
            saucePage.clickLoginButton();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fail to click login button");
        }
    }

    @Then("Verifies the product page header {string}")
    public void verifiesTheProductPageHeader(String header) {
        try {
            saucePage.verifyText(saucePage.getPage_header().getText(), header);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fail to verify page header.\n Actual is : "
                    + saucePage.getPage_header().getText() + "Expected was : " + header);
        }
    }

    @Then("Verifies the alert message {string}")
    public void verifiesTheAlertMessage(String message) {
        try {
            saucePage.verifyText(saucePage.getAlert_message().getText(), message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fail to verify alert message.\n Actual is : "
                    + saucePage.getAlert_message().getText() + "Expected was : " + message);
        }
    }
}

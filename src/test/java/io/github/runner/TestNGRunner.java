package io.github.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features"
        , glue = {"io.github.stepdefinitions", "io.github.hooks"}
        , plugin = "pretty"
        , monochrome = true
        , tags = "@login"
        , publish = true
)
public class TestNGRunner extends AbstractTestNGCucumberTests {
}

package com.nttdata.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        //ruta del reporte
        plugin = {"html:target/classes/cucumber-report.html",
                "json:target/classes/cucumber.json"},
        //stepNotifications = true,
        //plugin = {"pretty"},
        features = "src/test/resources/features",
        glue = "com.nttdata.stepsdefinitions",
        tags ="@CompraProductosMasivo"
)
public class CucumberTestSuite {
}

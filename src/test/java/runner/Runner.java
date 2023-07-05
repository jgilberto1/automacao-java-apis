package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import support.context.report.server.controller.ActionController;

@RunWith(Cucumber.class)
@CucumberOptions(
		 tags = ("@build"),
		 glue = {"hooks", "steps"},
		 plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
				 	"pretty","json:target/json-cucumber-reports/cucumber.json",
				 	"junit:target/xml-junit/junit.xml"},
		features = {"src/test/resources/features"})
public class Runner {
	@AfterClass
	public static void tearDownAll(){
		new ActionController().sendResults();
	}
}

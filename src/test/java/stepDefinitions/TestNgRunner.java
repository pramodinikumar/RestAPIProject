package stepDefinitions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = "classpath:features",
		glue="stepDefinitions",
		tags="@smoke" ,
		plugin = {"html:target/cucumber-pretty"}
		)
public class TestNgRunner extends AbstractTestNGCucumberTests {

}

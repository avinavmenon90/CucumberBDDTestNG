package Runner;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions (
		features = "C:\\Users\\Avinav\\eclipse-workspace\\CucumberBDDTestNG\\src\\main\\java\\Features\\login.feature", //location of feature file
		glue= {"stepDefinitions"}, //path of step definition file
		//glue= {"C:\\Users\\Avinav\\eclipse-workspace\\CucumberBDDFramework\\src\\main\\java\\stepDefinition\\CartStepDefinition.java"},
		format = {
				  "pretty",
				  "html:target/cucumber-reports/cucumber-pretty",
				  "json:target/cucumber-reports/CucumberTestReport.json",
				  "rerun:target/cucumber-reports/rerun.txt"}, //generate different types of reporting formats
		plugin = "json:target/cucumber-reports/CucumberTestReport.json",
		dryRun = false)


//standard code applicable to all Cucumber-TestNG setup
public class TestRunner {
    private TestNGCucumberRunner testNGCucumberRunner;
 
    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
 
    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }
 
    @DataProvider //this method is used to find number of feature files available to exec thru TestNG
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }
 
    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }
}
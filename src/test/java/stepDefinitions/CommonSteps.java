package stepDefinitions;

import Pages.CommonMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.io.IOException;

public class CommonSteps
{
    public CommonMethods cm = new CommonMethods();
    @Given("User launch {string} application")
    public void launchApp(String appName) throws InterruptedException {
        cm.launchApp(appName);
    }
    @When("User enter {string} in the {string} field on {string} page")
    public void enter_value_into_input_field(String value, String element, String page) throws IOException, InterruptedException {
        cm.enter_input_element(value, element, page);
    }

    @When("User validate {string} is displayed in {string} on {string} page")
    public void validate_text_element(String expected, String element, String page) throws IOException {
        cm.validateText(expected, element, page);
    }

    @When("User click on {string} on {string} page")
    public void click_on_element(String element, String page) throws IOException {
        cm.click_on_element(element, page);
    }

    @When("User selects option {string} from {string} dropdown on {string} page")
    public void select_option_from_Dropdown(String option, String element, String page) throws IOException, InterruptedException {
        cm.select_from_dropdown(option, element, page);
    }

    @When("User validate page title")
    public void validate_Title() throws InterruptedException {
        cm.pageTitleValidation();
    }
}

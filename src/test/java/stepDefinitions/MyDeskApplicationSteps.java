package stepDefinitions;

import Pages.MyDeskApplication;
import io.cucumber.java.en.When;

import java.io.IOException;

public class MyDeskApplicationSteps
{
    MyDeskApplication mydesk = new MyDeskApplication();

    @When("User enter pin+rsa test code in {string} field on {string} page")
    public void input_pin_and_rsa(String element, String page) throws IOException, InterruptedException {
    mydesk.enter_pin_Rsa_input(element, page);
    }
}

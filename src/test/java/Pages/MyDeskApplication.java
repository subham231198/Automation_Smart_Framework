package Pages;

import io.cucumber.messages.types.Hook;
import org.openqa.selenium.WebDriver;
import stepDefinitions.Hooks;

import java.io.IOException;
import java.util.Random;

public class MyDeskApplication
{
    Random random = new Random();
    CommonMethods cm = new CommonMethods();


    public void enter_pin_Rsa_input(String element, String page) throws IOException, InterruptedException {
        int pin = 100000 + random.nextInt(900000);
        String pin_value = Integer.toString(pin);

        int secur = 100000 + random.nextInt(900000);
        String secur_value = Integer.toString(secur);

        StringBuilder sb = new StringBuilder();
        sb.append(pin_value).append(secur_value);
        String value = sb.toString();

        cm.enter_input_element(value, element, page);
    }
}

package Utilities;

import io.cucumber.java.Scenario;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class MailerConfigReport
{
    private static Map<String, String> scenarioStatusMap = new HashMap<>();
    private static StringBuilder table = new StringBuilder();

    public void mailerReport(Scenario scenario) throws IOException {
        scenarioStatusMap.put(scenario.getName(), scenario.getStatus().toString());
        int totalScenarios = scenarioStatusMap.size();
        int passedScenarios = 0;
        int failedScenarios = 0;

        for (String status : scenarioStatusMap.values()) {
            if (status.equals("PASSED")) {
                passedScenarios++;
            } else if (status.equals("FAILED")) {
                failedScenarios++;
            }
        }

        String subject;
        String overallStatus;
        if (passedScenarios == totalScenarios) {
            subject = "Automation Test Execution Report (Overall Status: GREEN)";
            overallStatus = "GREEN";
        } else if (failedScenarios > 0) {
            subject = "Automation Test Execution Report (Overall Status: AMBER)";
            overallStatus = "AMBER";
        } else {
            subject = "Automation Test Execution Report (Overall Status: RED)";
            overallStatus = "RED";
        }
        table.setLength(0);
        table.append("Hi team, please find the automated test execution report attached.\n");

        for (Map.Entry<String, String> entry : scenarioStatusMap.entrySet()) {
            String scenarioName = entry.getKey();
            String status = entry.getValue();
            table.append("\nScenario: "+scenarioName).append(" Status: "+status);
            table.append("\n");
        }

        table.append("\n");
        table.append("Overall Status: ").append(overallStatus).append("\n");

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDateTime = date.format(formatter);
        table.append("Date: "+formattedDateTime);

        String body = table.toString();
        Mailer.sendEmail(subject, body);
    }
}

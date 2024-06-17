Feature: Test Feature

  @TC_001
  Scenario: Login functionality test
    Given User launch "Morgan Stanley" application
    When User enter "mukhesub" in the "username_field" field on "MorganStanley" page
    And User enter "admin@123" in the "password_field" field on "MorganStanley" page
    And User enter pin+rsa test code in "securid_field" field on "MorganStanley" page
    Then User click on "btn_Go" on "MorganStanley" page
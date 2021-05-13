Feature: Search for air quality data

  Scenario: Search air quality data by city name
    Given an application just turned on
    When a user goes to the homepage
    And types "Aveiro" in the city name search bar
    And clicks Enter
    Then "Aveiro" should appear in the header
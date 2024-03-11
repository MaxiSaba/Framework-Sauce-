@WebTesting @Sauce
Feature: Login

  Scenario: User ecommerce login
    Given I am in app main site
    Then I set valid credentials
    When I validate that user is logged in
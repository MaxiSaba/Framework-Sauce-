@WebTesting @Sauce
Feature: Login

  Scenario Outline: Login in with valid credentials
    Given user is on the login page
    Then user enters <username> and <password>
    And user clicks the login button
    When user should see the homepage

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |

  Scenario Outline: Try to loggin in with locked credentials
    Given user is on the login page
    Then user enters <username> and <password>
    And user clicks the login button
    When user should see the Epic sadface message

    Examples:
    | username       | password     |
    | locked_out_user | secret_sauce |

  Scenario: Try to loggin in without credentials
    Given user is on the login page
    When user clicks the login button
    Then user should see the credentials are requided message

  Scenario Outline: Add to cart from the product detail page
    Given user is logged in with <username> and <password>
    When user adds the product from the product detail page
    Then the product should be added tho the cart
    Examples:
      | username       | password    |
      | standard_user | secret_sauce |

  Scenario Outline: Click on an item in the cart
    Given user is logged in with <username> and <password>
    When user adds product to the cart
    And user clicks on the product in the cart
    Then user should be taken to the product detail page
    Examples:
      | username       | password    |
      | standard_user | secret_sauce |


  Scenario Outline: Remove item from the product detail page
    Given user is logged in with <username> and <password>
    When user adds product to the cart
    And user removes the product from the product detail page
    Then the product should be removed from the cart
    Examples:
      | username       | password    |
      | standard_user | secret_sauce |
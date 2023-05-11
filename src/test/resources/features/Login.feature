@login
Feature: SauceDemo Application - Login

  Background: Verifies Login Page
    Given Verifies user is on login page

  @valid_creds
  Scenario Outline: Login with valid credentials
    When Enters username "<username>"
    And Enters password "<password>"
    And Clicks on login button
    Then Verifies the product page header "<header>"

    Examples:
      | username      | password         | header   |
      | standard_user | c2VjcmV0X3NhdWNl | Products |

  @lock_creds
  Scenario Outline: Login with locked user credentials
    When Enters username "<username>"
    And Enters password "<password>"
    And Clicks on login button
    Then Verifies the alert message "<message>"

    Examples:
      | username        | password         | message                                             |
      | locked_out_user | c2VjcmV0X3NhdWNl | Epic sadface: Sorry, this user has been locked out. |

  @no_creds
  Scenario Outline: Login with no credentials
    When Clicks on login button
    Then Verifies the alert message "<message>"

    Examples:
      | message                            |
      | Epic sadface: Username is required |

  @no_pswd
  Scenario Outline: Login with no password
    When Enters username "<username>"
    And Clicks on login button
    Then Verifies the alert message "<message>"

    Examples:
      | username        | message                            |
      | locked_out_user | Epic sadface: Password is required |
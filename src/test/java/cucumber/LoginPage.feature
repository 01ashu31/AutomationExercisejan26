@tag
Feature: Login with the user
I want login and perform some action and then logout account

  Background:
    Given Landed on HomePage and click on login button

  @tag2
  Scenario Outline: positive test for register user
    Given logged in with username <name> and password <password>
    When click on logout button
    Then confirmation message is displayed.

  Example:
  |name                |password
  |ashutest@gmail.com  |test@123


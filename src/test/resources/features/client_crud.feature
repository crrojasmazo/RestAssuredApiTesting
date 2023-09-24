@active
Feature: Client testing CRUD

  Scenario: Get the List of Clients
    Given there are registered clients in the system
    When I send a GET request to view all the clients
    Then the response should have a status code of 200
    And validates the response with the client list JSON schema
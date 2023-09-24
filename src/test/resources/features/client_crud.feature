@active
Feature: Client testing CRUD

  Scenario: Get the List of Clients
    Given there are registered clients in the system
    When I send a GET request to view all the clients
    Then the response should have a status code of 200
    And validates the response with the client list JSON schema

  Scenario: Create New Client
    Given I have a client with the following details:
    | Name  | LastName   | Country | City | Email                | Phone     |
    | alejo | lopez      | Colombia| Cali | alejo.lopez@email.com| 1234567890|
    When I send a POST request to create a client
    And the response should have a status code 201
    And the response should include the details of the created client
    And validates the response with the client JSON schema
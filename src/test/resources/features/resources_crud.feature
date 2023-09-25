@active
Feature: Resources testing CRUD

  Scenario: Get the List of resources
    Given there are registered resources in the system
    When I send a GET request to view all the resources
    Then the response should have a status code of 200
    And validates the response with the resource list JSON schema

  Scenario: Update the Last Resource
    Given there are registered resources in the system
    And I retrieve the details of the latest resource
    When I send a PUT request to update the latest resource
    """
    {
    "name": "Thug Pants",
    "trademark": "Colorado jeans",
    "stock": 1000,
    "price": 99.99,
    "description":"Blue short jeans",
    "tags": "thug",
    "is_active": true
    }
    """
    Then the response should have a status code of 200
    And the response should have the following details:
    | name | trademark | stock | price | description | tags | is_active |
    | Thug Pants | Colorado jeans | 1000 | 99.99 | Blue short jeans | thug | true |
    And validates the response with the resource JSON schema
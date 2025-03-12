Feature: Object Management in the API rest

  Scenario: Retrieve all existing objects
    When the user retrieves all available objects
    Then the user should see status code 200
    And the user should see a list of objects in the response

  Scenario: Create a new custom object
    Given the user has the following data to create an object:
      | name            | data                                       |
      | Custom 1 Object | {"description":"Test object","price":5.99} |
    When the user creates a new object
    Then the user should see status code 200
    And the user should see that the object has the name "Custom 1 Object"

  Scenario: Retrieve an object by its ID
    Given the user retrieves the object with ID "1"
    Then the user should see status code 200
    And the user should see that the object's name is "Google Pixel 6 Pro"

  Scenario: Retrieve multiple objects by their IDs
    Given the user retrieves the objects with the following IDs:
      | 1 |
      | 6 |
    Then the user should see status code 200
    And the user should see a list with 2 objects

  Scenario: Delete an existing object by ID
    Given the user creates a new object with the following data:
      | name           | data                                       |
      | Temp Object    | {"description":"Temp for deletion"}        |
    When the user deletes the object by its ID
    Then the user should see status code 200
    And the object should no longer exist when queried by its ID

  Scenario: Try to delete an object with an invalid ID
    When the user deletes the object with ID "id-123"
    Then the user should see the status code 404

  Scenario: Create an object without a name
    Given the user has the following data to create an object:
      | name | data                                |
      |      | {"description":"No name field"}     |
    When the user creates a new object
    And the response should contain an error message "Name field is required"




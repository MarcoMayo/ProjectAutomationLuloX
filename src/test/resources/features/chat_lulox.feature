Feature: Interaction with of chat LuloX

  Scenario Outline: The user interacts with the support chat
    Given the user opens the LuloX homepage
    When the user sends the message "<message>" through the chat
    Then the user should see the sent message "<message>" displayed in the chat

    Examples:
      | message                   |
      | Hello, I want information |

  Scenario Outline: The user interacts with the quick reply
    Given the user opens the LuloX homepage
    When the user selects the quick reply option "<message>"
    Then the user should see the sent message "<message>" displayed in the chat

    Examples:
      | message                       |
      | Tengo problemas con mi compra |

  Scenario: The user changes their name in the chat
    Given the user opens the LuloX homepage
    When the user changes their name to "QA Tester"
    Then the user should see their name updated to "QA Tester" in the chat
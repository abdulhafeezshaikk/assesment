Narrative:
As a system,
I want to create 1 user and 2 cards
So that I can do transactions

Scenario: create user and 2 cards
Meta:
@test

Given I am TEST
When create user core api operation is invoked
Then I get a successful response
Given I am a us only individual user
And I want a perfect plastic physical atm enabled card
When create card operation is invoked
Then my atm enabled custom card is created
Given I am a us only individual user
And I want a perfect plastic physical atm enabled card
When create card operation is invoked
Then my atm enabled custom card is created
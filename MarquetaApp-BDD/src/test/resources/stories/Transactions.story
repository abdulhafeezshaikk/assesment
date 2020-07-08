Narrative: 
As a User, 
I want to transact using marqueta card
So that I can see why it's awesome

Scenario: 01 make transaction using a inactive card with funds in pending credits
Meta:
@transactions
@acceptance

Given I have a inactive card with funds still in pending credits
When transaction operation is called
Then I get response status as DECLINED
And response as Card not active, code as 1806

Scenario: 02 make transaction using a virtual card with insufficient funds
Meta:
@transactions
@acceptance

Given I have a card with funds still in pending credits
When transaction operation is called
Then I get response status as DECLINED
And response as Not sufficient funds, code as 1016
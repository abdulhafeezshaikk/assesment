Narrative: 
As a User, 
I want to create a card 
So that I can conduct transactions at merchants

Scenario: 01 create a perfect plastic physical atm enabled card for a individual user in usa
Meta: 
@acceptance
@createcard

Given I am a us only individual user
And I want a perfect plastic physical atm enabled card
When create card operation is invoked
Then my atm enabled custom card is created

Scenario: 02 create a digital wallet enabled card for a individual and expedite the shipping
Meta: 
@acceptance
@createcard

Given I am a us only individual user
And I want a digital wallet enabled card
And I want a to expedite shipping
When create card operation is invoked
Then my digital wallet enabled card is created
And shipping is expedited

Scenario: 03 create a virtual only card with postal code address validation for a parent-child user account 
Meta: 
@acceptance
@createcard

Given I am a parent-child user account
And I want virtual card with address validation on postal code
When create card operation is invoked
Then virtual card is created

Scenario: 04 create a personalized card with user's name, image and signature and update the users address
Meta: 
@acceptance
@createcard

Given I am a individual customer
And I want to update my <Address><City>
And I want to add my <Name><Image> and <Signature>
When create card operation is invoked
Then my personalized card is created
And address is update to <Address><City>
And <Name><Image> and <Signature> are added to the card
Examples:
|		Address		|		City		|		Name		|		Image		|		Signature		|
| 	1 test avenue	|	testCity		|	My Test Name	|	my_image.png	|	my_signature.png	|


Scenario: 05 create a card with card product that doesnt allow create card
Meta: 
@acceptance
@createcard

Given I am a individual user
And card product doesnt allow create card
When create card operation is invoked
Then error message Unable to create card when allow_card_creation is set to false is thrown
And error code is 400659
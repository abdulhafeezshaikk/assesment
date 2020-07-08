Narrative: 
As a User, 
I want to create a card products
So that behavior of associated cards can be determined whether they can be used at an ATM and/or online and whether they are currently enabled

Scenario: 01 create a atm/ecommerce card product
Meta:
@acceptance
@createcardproduct
@atmecommerce

Given I want to create <CardType> card
When create card product is invoked
Then I get a successful <CardType> response
Examples:
| CardType	|
| 	atm		|
| ecommerce	|

Scenario: 02 create a card product that sends auth messages for address verification for postal code mismatch 
Meta:
@acceptance
@createcardproduct
@authmessages

Given user creates a card product
And enables auth messages for address verification on postal code
When create card product is invoked
Then address verification messages are enabled for the card

Scenario: 03 create perfect plastic/Idemia and arrow eye cards with
Meta:
@acceptance
@createcardproduct
@allproviders

Given user creates <CardName>
And wants fulfilment provider as <Provider>
When create card product is invoked
Then a <CardName> product is created with <Provider>
Examples:
|		CardName		|   	Provider		|
| perfectplasticcard	|   PERFECTPLASTIC		|
|	  idemiacard		|   	IDEMIA			|
|	arroweyecard		|   	ARROWEYE		|

Scenario: 04 create a card product that doesnt allow card creation 
Meta:
@acceptance
@createcardproduct
@createcarddisable

Given user creates a card product
And the product doesnt allow card creation
When create card product is invoked
Then card creation is disabled for the product

Scenario: 05 create a card product with no shipping/return address
Meta:
@acceptance
@createcardproduct
@noaddress

Given user creates a card product with no address
When create card product is invoked
Then card is created with no address


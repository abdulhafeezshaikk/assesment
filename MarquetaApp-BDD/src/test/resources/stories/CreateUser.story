Narrative: 
As a User, 
I want to create a user accounts
So that parent-child relationship between two users or business and user are established

Scenario: 01 Create an individual user account with no child users
Meta:
@individualuser

Given I am an individual user
And I have no child users
When create user core api operation is invoked
Then I get a successful response

Scenario: 02 Create a new parent user account with 2 child users
Meta:
@newparentuser2users

Given I am a new parent user
When create user core api operation is invoked
Then parent account is created
And I have <User> child account
And user <Shares> balances with parent
When create user core api operation is invoked
Then <User> is created with balance <Shares> preference

Examples:
|   User 	|   Shares 	|
| userOne	|	true	|
| userTwo	|	false	|

Scenario: 03 Create a child user account for an existing parent user
Meta:
@addchild2existingparent

Given parentuserr_token is an existing user/account
And a newChildAccount is added to parentuserr_token account
When create user core api operation is invoked
Then the newChildAccount account is created for parentuserr_token account


Narrative: 
As a User, 
I want to create a user accounts
So that parent-child relationship between two users or business and user are established

Scenario: 01 Create an individual user account with no child users
Meta:
@acceptance
@createuser
@individualuser

Given I am an individual user
And I have no child users
When create user core api operation is invoked
Then I get a successful response

Scenario: 02 Create a new parent user account with 2 child users
Meta:
@acceptance
@createuser
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
@acceptance
@createuser
@addchild2existingparent

Given parentuserr_token is an existing user/account
And a newChildAccount is added to parentuserr_token account
When create user core api operation is invoked
Then the newChildAccount account is created for parentuserr_token account

Scenario: 04 Create a child user account for an existing parent user
Meta:
@acceptance
@createuser
@addgrandchild2nonbusinessacount
Given newChildAccount is an existing child user
And add a grandChildAccount to a non business newChildAccount account
When create user core api operation is invoked
Then error message Children of a User Cardholder may not have children is received
And error code 400064 is received

Scenario: 05 Error validations for email,token
Meta:
@acceptance
@createuser
@errors

Given I am an individual user
And user tries to create an account with existing <Field>
When create user core api operation is invoked
Then user receives <ErrorCode> and <ErrorMessage>

Examples:
| 	Field			|	  ErrorCode			|		ErrorMessage								|
|	email			|		400057			|	A card holder with the same email already exist |
|	token			|		409002			|	General conflict exception: Token has already been associated with a different payload |


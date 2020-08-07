Feature: MoneyMovement Test Cases


Background: 
Given Initiate the setup required



Scenario: Initiate_the_Account_API_with_valid_fields_and_verify_the_Expected_Result

When Initiate the Client_Credentials_API  and get the accessToken
And Initiate the EtoEkey_API  and get the Biz_token
And Initiate the Resource_Owner and get the encrypted password
Then Initiate the Accounts_API with '<client_id>' and '<access_token>' to get all the accounts 


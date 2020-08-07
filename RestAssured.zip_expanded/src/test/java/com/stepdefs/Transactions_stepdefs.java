package com.stepdefs;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.api.service.Base_MoneyMovement;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Transactions_stepdefs {

	Base_MoneyMovement MM = new Base_MoneyMovement();
	
	
	@Given("Initiate the setup required")
	public void initiate_the_setup_required() {
	    // Write code here that turns the phrase above into concrete actions
	   MM.baseURI_setup();
	}
	

	
	@When("Initiate the Client_Credentials_API with '(.*)' and '(.*)'  get the accessToken$")
	public void initiate_the_Client_Credentials_API_with_and_get_the_accessToken(String string, String string2) throws FileNotFoundException, IOException {
		
		MM.API_clientCredentials(string, string2);
		
	}
	
	@When("Initiate the EtoEkey_API  and get the Biz_token")
	public void API_e2ekey() {
		MM.API_e2ekey();
	}
	


	@When("Initiate the Resource_Owner with {string} and {string} and get the encrypted password")
	public void initiate_the_Resource_Owner_with_and_and_get_the_encrypted_password(String string, String string2) throws FileNotFoundException, IOException {
		System.out.println("string2--- "+string2);
		MM.API_ResourceOwner(string,string2);
	}


	@When("Initiate the Accounts_API with {string} and {string} to get all the accounts")
	public void initiate_the_Accounts_API_to_get_all_the_accounts(String clientid, String accessToken) {
		MM.API_Accounts(clientid,accessToken);
	}

	
	@Then("Initiate the card_API with {string} to get card details")
	public void initiate_the_card_API_to_get_card_details(String s) {
	  MM.API_Cards(s);
    }
	
	@Then("Initiate the Transactions_API with {string} and {string} to get transactions details")
	public void initiate_the_Transactions_API_to_get_transactions_details(String accId,String transactionStatus ) {
	    MM.API_Transactions(accId, transactionStatus);
	}

	@Then("Initiate the sourceAccounts & destination Eligibility API get details")
	public void initiate_the_sourceAccounts_destination_Eligibility_API_get_details() {
	   MM.PDT_Confirmation_API();
	}
		
	
	
	@Then("Initiate the PDT-PreProcess_API and get details")
	public void initiate_the_PDT_PreProcess_API_and_get_details() {
		 MM.PDT_Process();
	}
	
	@Then("Initiate the PDT-PreProcess_API with invalid sourceAccountId")
	public void initiate_the_PDT_PreProcess_API_with_invalid_sourceAccountId() {
		 MM.PDT_Process_invalidSAcc();
	}
	
	@Then("Initiate the PDT_Confirmation_API and get details")
	public void initiate_the_PDT_Confirmation_API_and_get_details() {
	  MM.PDT();
	}
	
	@Then("Initiate the PDT_Confirmation_API with invalid controlFlowId")
	public void initiate_the_PDT_Confirmation_API_with_invalid_controlFlowId() {
		 MM.PDT_negativeCase();
	}
	
	
	
	}

	
	



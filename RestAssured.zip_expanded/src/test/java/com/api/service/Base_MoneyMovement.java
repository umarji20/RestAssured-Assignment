package com.api.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import java.util.UUID;


import org.apache.http.entity.ContentType;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.accounts.*;
import com.jayway.jsonpath.DocumentContext;

import PropertyFile.PropertyFile;
import citibankapi.Trasnferaccounts.DestinationSourceAcctCombination;
import citibankapi.Trasnferaccounts.Sourceanddesinationaccount;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;



public class Base_MoneyMovement {
	String authorization_64;
	String client_id;
	String client_secret;
	String access_token;
	String BizToken;
	String Authorization_new;
	String access_token_new;
	String excelFilePath="src\\main\\java\\data1\\assign3Data.xlsx";
	
@BeforeTest
public void baseURI_setup() {
	RestAssured.baseURI=PropertyFile.getValue("MM_baseURI");
	authorization_64 = PropertyFile.getValue("MM_authorization_64");
	client_id= PropertyFile.getValue("MM_client_id");
	client_secret = PropertyFile.getValue("MM_client_secret");
	
}

@Test
public void API_clientCredentials(String grantType,String scope) throws FileNotFoundException, IOException {
	
	System.out.println("Start of the API >>>  /gcb/api/clientCredentials/oauth2/token/sg/gcb");
    String payload = "grant_type="+grantType+"&scope="+scope;
  
    RequestSpecification request = RestAssured.given();
    Response response = request
            .when()
            .contentType("application/x-www-form-urlencoded")          
            .header("Authorization",authorization_64)
            .header("uuid",UUID.randomUUID().toString())
            .accept(ContentType.APPLICATION_JSON.getMimeType())
            .with()
            .body(payload)
            .post("/gcb/api/clientCredentials/oauth2/token/us/gcb");
           // .post("/gcb/api/clientCredentials/oauth2/token/us/gcb");
           


    System.out.println(response.statusCode());
    System.out.println(response.body().prettyPrint());

    ResponseBody body = response.body();
    DocumentContext jsonContext = com.jayway.jsonpath.JsonPath.parse(body.print());
     access_token = jsonContext.read("$.access_token");
     System.out.println("End of API >>>>   /gcb/api/clientCredentials/oauth2/token/sg/gcb");
}

@Test
public void API_e2ekey() {
	 System.out.println("Start of the API >>>  /gcb/api/security/e2eKey");
	 RequestSpecification request = RestAssured.given(); 
     String Authorization = "Bearer" + " " + access_token;
     
     System.out.println("Authorization------- "+Authorization);
     Response response = request
             .when()
             .header("Authorization",Authorization)
             .header("client_id",client_id)
             .header("client_secret",client_secret)
             .header("uuid",UUID.randomUUID().toString())
             .accept("application/json")
             .contentType(ContentType.APPLICATION_JSON.getMimeType())
             .header("countryCode","PL")
             .header("businessCode","GCB")
             .header("channelId","SSA")
             .with()
             //.get(EEmethod);
             .get("/gcb/api/security/e2eKey");

     System.out.println(response.statusCode());
     System.out.println(response.body().prettyPrint());

      BizToken =  response.header("bizToken").toString();

     System.out.println("End of API >>>>   /gcb/api/security/e2eKey");
	
}

@Test
public void API_ResourceOwner(String uName, String pass) throws FileNotFoundException, IOException {
	System.out.println("Start of the API >>>  /gcb/api/password/oauth2/token/bh/gcb");
	RequestSpecification request = RestAssured.given();
   // String username ="SandboxUser1";
    String encrypted_string = "967c2812d7c437b1b8f5dcc887ab64eb24637c9855cc5adeae232c5ae68232802579af2dad68e94c09d3cd6ec3f974e060468150c5ad8c01f39830a723229e8507e9a23433ff273cb6084cf12e838e76e30b2cba1e23ff7be9567726e584f5f442865553df94ddda1ea035e17e3109fcfd9ea3a1d5499a184ca7b79964ca5adfeac59b566f52fb4aa0e7b35983c71b838d16d3dff61715bafde60328e9c5c7fe5ae0e5edc8187d0167206b59df38e718deef822a0499d128d1d6a8464d0871bcdc273658d3c73ebda9c3e348b6e16838942d4630d555ff3ca6569d409b674c37e6b2412e086b644844662dd36fa79acb467207855a39e203552b9f1d2b0e1600";
   String payload1 = "grant_type=password&scope=/api&username="+uName+"&password="+pass;

    System.out.println("==========payload1========= "+payload1);
    Response response = request
            .when()
            .contentType("application/x-www-form-urlencoded")
            .header("Authorization",authorization_64)
            .accept("application/json")
            .header("uuid",UUID.randomUUID().toString())
            .header("BizToken",BizToken)
            .with()
            .body(payload1)
            //.post(ROmethod);
            .post("/gcb/api/password/oauth2/token/bh/gcb");

    System.out.println(response.statusCode());
    System.out.println(response.body().prettyPrint());

    ResponseBody body = response.body();
    DocumentContext jsonContext_new = com.jayway.jsonpath.JsonPath.parse(body.print());
     access_token_new= jsonContext_new.read("$.access_token");

     Authorization_new = "Bearer "+access_token_new;

    System.out.println("End of the API >>>  /gcb/api/password/oauth2/token/bh/gcb");
	
}

@Test
public Response API_Accounts(String clientId,String accessToken) {
	System.out.println("Start of the API >>>  /gcb/api//v1/accounts");

	System.out.println("clientId-------- "+clientId);
	System.out.println("accessToken-------- "+accessToken);
	 RequestSpecification request= RestAssured.given();

	 Response response = request
            .when()
            .header("Authorization",access_token_new)
            .accept("application/json")
            .header("uuid",UUID.randomUUID().toString())
            .header("client_id",client_id)
            .with()
            .get("/gcb/api/v1/accounts");

    System.out.println(response.statusCode());
    System.out.println(response.body().prettyPrint());

    System.out.println("End of the API >>>  /gcb/api/v1/accounts");
	return response;

 }

public void API_Cards(String cardFun) {
	

	System.out.println("Start of the API >>>  /gcb/api//v1/cards");
	
	RequestSpecification request= RestAssured.given();
	
	Response response = request
	.when()
	.header("Authorization",Authorization_new)
	.accept("application/json")
	.header("uuid",UUID.randomUUID().toString())
	.header("client_id",client_id)
	.queryParam("cardFunction", cardFun)
	.with()
	.get("/gcb/api/v1/cards");
	
	System.out.println(response.statusCode());
	System.out.println(response.body().prettyPrint());
	
	System.out.println("End of the API >>>  /gcb/api/v1/cards");
	


}


@Test
public void API_Transactions(String accId,String transactionStatus) {
	 

	// String accountId=transAccountID();
	// System.out.println("under API_Transactions---- "+accountId);
       System.out.println("Start of the API >>> /gcb/api//v1/accounts/transactions");
       
       RequestSpecification request = RestAssured.given();
       Response response = request
       .when()
       .header("Authorization",Authorization_new)
       .accept("application/json")
       .header("uuid",UUID.randomUUID().toString())
       .header("Client_id",client_id)
       .queryParam("transactionStatus", transactionStatus)
       .with()
       .get("/gcb/api/v1/accounts/"+accId+"/transactions");
       
       System.out.println(response.statusCode());
       System.out.println(response.body().prettyPrint());
       System.out.println("End of the API >>>  /gcb/api//v1/accounts/transactions");

 }



@Test
    public Response PDT_Confirmation_API() {
    
	System.out.println("Start of the API >>>   /gcb/api/v1/moneyMovement/personalDomesticTransfers/destinationAccounts/sourceAccounts");
    RequestSpecification request = RestAssured.given();
    Response response = request
            .when()
            .header("Authorization",Authorization_new)
            .accept("application/json")
            .header("uuid",UUID.randomUUID().toString())
            .header("Client_id",client_id)
            .with()
            .get("/gcb/api/v1/moneyMovement/personalDomesticTransfers/destinationAccounts/sourceAccounts");

    System.out.println(response.statusCode());
    System.out.println(response.body().prettyPrint());
    System.out.println("End of the API >>>  /gcb/api/v1/moneyMovement/personalDomesticTransfers/destinationAccounts/sourceAccounts");
	return response;


    
}
//
//public void PDT_Process(String sourceId, String destId) {
//	
//	System.out.println("End of the API >>>  /gcb/api/v1/moneyMovement/personalDomesticTransfers/preprocess");
//	
//	JSONObject payload = new JSONObject();
//	
//	
//	payload.put("sourceAccountId",sourceId);
//	payload.put("transactionAmount","400");
//    payload.put("transferCurrencyIndicator","SOURCE_ACCOUNT_CURRENCY");  
//    payload.put("destinationAccountId",destId);
//    payload.put("chargeBearer","BENEFICIARY");
//    payload.put("fxDealReferenceNumber","123456");
//    payload.put("remarks","1");
//
//
//    RequestSpecification request = RestAssured.given();
//    Response response = request
//            .when()
//            .header("Authorization",Authorization_new)
//            .accept("application/json")
//            .contentType(ContentType.APPLICATION_JSON.getMimeType())
//            .header("uuid",UUID.randomUUID().toString())
//            .header("Client_id",client_id)
//            .with()
//            .body(payload.toString())
//            .post("/gcb/api/v1/moneyMovement/personalDomesticTransfers/preprocess");
//
//    System.out.println(response.statusCode());
//    ResponseBody body2 = response.body();
//    DocumentContext jsonContext2 = com.jayway.jsonpath.JsonPath.parse(body2.print());
//    String controlFlowID = jsonContext2.read("$.controlFlowId");
//    System.out.println("End of the API >>>  /gcb/api/v1/moneyMovement/personalDomesticTransfers/preprocess");
//}

public Response PDT_Process() {
	
	System.out.println("End of the API >>>  /gcb/api/v1/moneyMovement/personalDomesticTransfers/preprocess");
	
	JSONObject payload = new JSONObject();
	
	ArrayList<String> accountId=getAccountID();
	payload.put("sourceAccountId",accountId.get(0));
	payload.put("transactionAmount","400");
    payload.put("transferCurrencyIndicator","SOURCE_ACCOUNT_CURRENCY"); 
    payload.put("destinationAccountId",accountId.get(1));
    payload.put("chargeBearer","BENEFICIARY");
    payload.put("fxDealReferenceNumber","123456");
    payload.put("remarks","1");


    RequestSpecification request = RestAssured.given();
    Response response = request
            .when()
            .header("Authorization",Authorization_new)
            .accept("application/json")
            .contentType(ContentType.APPLICATION_JSON.getMimeType())
            .header("uuid",UUID.randomUUID().toString())
            .header("Client_id",client_id)
            .with()
            .body(payload.toString())
            .post("/gcb/api/v1/moneyMovement/personalDomesticTransfers/preprocess");

    System.out.println(response.statusCode());
    
    System.out.println("End of the API >>>  /gcb/api/v1/moneyMovement/personalDomesticTransfers/preprocess");
	return response;
}

public Response PDT_Process_invalidSAcc() {
	
	System.out.println("End of the API >>>  /gcb/api/v1/moneyMovement/personalDomesticTransfers/preprocess");
	
	JSONObject payload = new JSONObject();
	
	ArrayList<String> accountId=getAccountID();
	payload.put("sourceAccountId","1111");
	payload.put("transactionAmount","400");
    payload.put("transferCurrencyIndicator","SOURCE_ACCOUNT_CURRENCY"); 
    payload.put("destinationAccountId",accountId.get(1));
    payload.put("chargeBearer","BENEFICIARY");
    payload.put("fxDealReferenceNumber","123456");
    payload.put("remarks","1");


    RequestSpecification request = RestAssured.given();
    Response response = request
            .when()
            .header("Authorization",Authorization_new)
            .accept("application/json")
            .contentType(ContentType.APPLICATION_JSON.getMimeType())
            .header("uuid",UUID.randomUUID().toString())
            .header("Client_id",client_id)
            .with()
            .body(payload.toString())
            .post("/gcb/api/v1/moneyMovement/personalDomesticTransfers/preprocess");

    System.out.println(response.statusCode());
    
    System.out.println("End of the API >>>  /gcb/api/v1/moneyMovement/personalDomesticTransfers/preprocess");
	return response;
}

public void PDT() {
	
	Response response=PDT_Process();
	ResponseBody body2 = response.body();
    DocumentContext jsonContext2 = com.jayway.jsonpath.JsonPath.parse(body2.print());
    String controlFlowID = jsonContext2.read("$.controlFlowId");
    
	System.out.println("Start of the API >>> /gcb/api/v1/moneyMovement/personalDomesticTransfers");
    String payload_ControlFlowID="{\n" +
            "  \"controlFlowId\": \""+controlFlowID+"\"\n" +
            "}";
    RequestSpecification request = RestAssured.given();
     response = request
            .when()
            .header("Authorization",Authorization_new)
            .accept("application/json")
            .contentType(ContentType.APPLICATION_JSON.getMimeType())
            .header("uuid",UUID.randomUUID().toString())
            .header("Client_id",client_id)
            .with()
            .body(payload_ControlFlowID)
            .post("/gcb/api/v1/moneyMovement/personalDomesticTransfers");

    System.out.println(response.statusCode());
    System.out.println(response.body().prettyPrint());
    System.out.println("End of the API >>>  /gcb/api/v1/moneyMovement/personalDomesticTransfers");

}

public void PDT_negativeCase() {
	
	Response response=PDT_Process();
	ResponseBody body2 = response.body();
    DocumentContext jsonContext2 = com.jayway.jsonpath.JsonPath.parse(body2.print());
    String controlFlowID = jsonContext2.read("$.controlFlowId");
    
	System.out.println("Start of the API >>> /gcb/api/v1/moneyMovement/personalDomesticTransfers");
    String payload_ControlFlowID="{\n" +
            "  \"controlFlowId\": \""+1234+"\"\n" +
            "}";
    RequestSpecification request = RestAssured.given();
     response = request
            .when()
            .header("Authorization",Authorization_new)
            .accept("application/json")
            .contentType(ContentType.APPLICATION_JSON.getMimeType())
            .header("uuid",UUID.randomUUID().toString())
            .header("Client_id",client_id)
            .with()
            .body(payload_ControlFlowID)
            .post("/gcb/api/v1/moneyMovement/personalDomesticTransfers");

    System.out.println(response.statusCode());
    System.out.println(response.body().prettyPrint());
    System.out.println("End of the API >>>  /gcb/api/v1/moneyMovement/personalDomesticTransfers");

}

public  ArrayList<String> getAccountID() {
	
	ArrayList<String> accountId=new ArrayList<String>();
	
	  Response response=PDT_Confirmation_API();
	  Sourceanddesinationaccount Sourceanddestinationaccount = response.getBody().as(Sourceanddesinationaccount.class);

	   // Get the source and destination account from the index directly if the account details are already known
	    
	    
	    String desitnationaccountid = Sourceanddestinationaccount.getDestinationSourceAcctCombinations().get(0).getDestinationAccountId();
	    System.out.println("********Destination account ID*************  : " + desitnationaccountid);
	    String sourceaccountId =Sourceanddestinationaccount.getDestinationSourceAcctCombinations().get(0).getSourceAccountIds().get(0).getSourceAccountId();
	    System.out.println("********Source account ID*************  : " + sourceaccountId);
	    System.out.println();
	    System.out.println();
	    accountId.add(sourceaccountId);
	    accountId.add(desitnationaccountid);
	    
	    // Get the source and destination account from the response using For loop when the source and destination account details are not known


	   for(int Index = 0; Index < Sourceanddestinationaccount.getDestinationSourceAcctCombinations().size(); Index++)
	    	
	        {
	    	               
	    	   DestinationSourceAcctCombination DestinationSourceAcctCombinations = Sourceanddestinationaccount.getDestinationSourceAcctCombinations().get(Index) ;
	    	    
	    	          	            
	              
	              String desitaccount = null;
	              
	                try {
	                	desitaccount = DestinationSourceAcctCombinations.getDestinationAccountId();
	                	//.getAccounts().get(accountIndex).getSavingsAccountSummary().getAccountId();
	                    //System.out.println("********Destination account ID*************  : " + desitaccount);
	                    
	                    
	                   
	                }catch(Exception e){
	                }
	                
	             for (int sosurceaccountindex = 0 ; sosurceaccountindex<DestinationSourceAcctCombinations.getSourceAccountIds().size();sosurceaccountindex++)
	                
	                {
	                	String sourceacc = null;
	                	try {
	                		sourceacc = DestinationSourceAcctCombinations.getSourceAccountIds().get(sosurceaccountindex).getSourceAccountId();
	                    	//.getAccounts().get(accountIndex).getSavingsAccountSummary().getAccountId();
	                        //System.out.println("********Source account ID*************  : " + sourceacc);
	                       
	                       
	                    }catch(Exception e){
	                    }
	                	
	                
	            } 
	        
	     }
	return accountId;
}



public String transAccountID() {
	  String accountId = null;
	
	Response response =API_Accounts(client_id, access_token);
	 AccountGroupSummaryList accountGroupSummaryList = response.getBody().as(AccountGroupSummaryList.class);


     for(int accountGroupSummaryListIndex = 0; accountGroupSummaryListIndex < accountGroupSummaryList.getAccountGroupSummary().size(); accountGroupSummaryListIndex++)
         {
             AccountGroupSummary accountGroupSummary = accountGroupSummaryList.getAccountGroupSummary().get(accountGroupSummaryListIndex);
             for(int accountIndex = 0; accountIndex < accountGroupSummary.getAccounts().size(); accountIndex++)
             {
               
                 try {
                     accountId = accountGroupSummary.getAccounts().get(accountIndex).getSavingsAccountSummary().getAccountId();
                     System.out.println(accountGroupSummary.getAccounts().get(accountIndex).getSavingsAccountSummary().getAccountId());
                 }catch(Exception e){
                 }

                 try {
                     accountId = accountGroupSummary.getAccounts().get(accountIndex).getCheckingAccountSummary().getAccountId();
                     System.out.println(accountGroupSummary.getAccounts().get(accountIndex).getCheckingAccountSummary().getAccountId());
                 }catch(Exception e){
                 }

                 try{
                     accountId = accountGroupSummary.getAccounts().get(accountIndex).getCreditCardAccountSummary().getAccountId();
                     System.out.println(accountGroupSummary.getAccounts().get(accountIndex).getCreditCardAccountSummary().getAccountId());
                 }catch(Exception e){
                 }

}
}
     return accountId;
}
}


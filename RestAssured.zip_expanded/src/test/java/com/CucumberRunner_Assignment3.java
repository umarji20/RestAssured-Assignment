package com;

import com.maveric.core.cucumber.CucumberBaseTest;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"./src/test/resources/features"},
        glue ={ "com.stepdefs"}
        
       
)

public class CucumberRunner_Assignment3 extends CucumberBaseTest{
	


	}

//baseclassfor APi - that contains all the improvised API's (request spec builder, request specification,filters,serialization/deserialization) 
//feature
//stepdef
//runner
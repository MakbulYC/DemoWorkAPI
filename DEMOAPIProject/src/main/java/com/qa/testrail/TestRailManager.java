package com.qa.testrail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

public class TestRailManager {
	
	public static String TEST_RUN_ID = "6";
	
	public static String TEST_RAIL_USERNAME = "makbul.khan@ezeetechnosys.com";
	public static String TEST_RAIL_PASSWORD = "Makbul@2024";
	
	public static String TEST_RAIL_ENGINE_URL = "https://yanoljacloud.testrail.io/";
	
	public static int TEST_CASE_PASS_STATUS = 1;
	public static int TEST_CASE_FAIL_STATUS = 5;
	
	public static void addResultForTestCase(String testCaseId, int status, String error) throws Exception, IOException, APIException {
		
		String testRunID = TEST_RUN_ID;
		APIClient client = new APIClient(TEST_RAIL_ENGINE_URL);
		client.setUser(TEST_RAIL_USERNAME);
		client.setPassword(TEST_RAIL_PASSWORD);
		
		Map<String, Object> data = new HashMap<String, Object>() ;
		data.put("status_id", status);
		data.put("comment", "This test in User should be login in Absolute" + error);
		
		client.sendPost("add_result_for_case/"+testRunID+"/"+testCaseId, data);
		
	}

	
}

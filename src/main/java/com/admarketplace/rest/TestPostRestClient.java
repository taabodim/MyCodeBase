package com.admarketplace.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.mongodb.BasicDBObject;

public class TestPostRestClient {

	public static void main(String[] args) throws ClientProtocolException, IOException {

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("https://localhost:8443/rest_services/amp/clickfilters/actions/edit/subj_rate");
		ArrayList<String> allCases = prepareAllCases();
		
		for(String requestPayload : allCases)
		{
		
		setTheHeaders(post);
		StringEntity input = new StringEntity(requestPayload);

		post.setEntity(input);
		HttpResponse response = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		while ((line = rd.readLine()) != null) {
			
			System.out.println(line);
		}
		BasicDBObject json = new BasicDBObject();
		
		}
	}
//	private String getATestCase(int value,boolean thisFilterGroup,Boolen allSubs,boolean asOverride, String comment,int trafficSourceId,int clickStatusId)
//	{
//		
//	}
	
	private static ArrayList<String> prepareAllCases() {
		ArrayList<String> allCases = new ArrayList<String>();
		
//		String getATestCase(int value,boolean thisFilterGroup,boolen allSubs,boolean asOverride, String comment,int trafficSourceId,int clickStatusId);
		String noOptionCase = "{\"value\":\"77\",\"thisFilterGroup\":false,\"allSubs\":false,\"asOverride\":false,\"comment\":\"asd\",\"tsId\":1060087,\"clickStatusId\":23}";
		allCases.add(noOptionCase);

		String thisFilterGroupCase = "{\"value\":\"77\",\"thisFilterGroup\":true,\"allSubs\":false,\"asOverride\":false,\"comment\":\"asd\",\"tsId\":1060087,\"clickStatusId\":23}";
		allCases.add(thisFilterGroupCase);

		String allSubsCase = "{\"value\":\"77\",\"thisFilterGroup\":false,\"allSubs\":true,\"asOverride\":false,\"comment\":\"asd\",\"tsId\":1060087,\"clickStatusId\":23}";
		allCases.add(allSubsCase);

		String asOverrideCase = "{\"value\":\"77\",\"thisFilterGroup\":false,\"allSubs\":false,\"asOverride\":true,\"comment\":\"asd\",\"tsId\":1060087,\"clickStatusId\":23}";
		allCases.add(asOverrideCase);

		String asOverrideAllSubsCase = "{\"value\":\"77\",\"thisFilterGroup\":false,\"allSubs\":true,\"asOverride\":true,\"comment\":\"asd\",\"tsId\":1060087,\"clickStatusId\":23}";
		allCases.add(asOverrideAllSubsCase);

//		String noOptionCase = "{\"value\":\"77\",\"thisFilterGroup\":true,\"allSubs\":false,\"asOverride\":true,\"comment\":\"asd\",\"tsId\":1060087,\"clickStatusId\":23}";
//		allCases.add(noOptionCase);
//
//		String noOptionCase = "{\"value\":\"77\",\"thisFilterGroup\":false,\"allSubs\":false,\"asOverride\":true,\"comment\":\"asd\",\"tsId\":1060087,\"clickStatusId\":23}";
//		allCases.add(noOptionCase);
//
//		String noOptionCase = "{\"value\":\"77\",\"thisFilterGroup\":false,\"allSubs\":false,\"asOverride\":true,\"comment\":\"asd\",\"tsId\":1060087,\"clickStatusId\":23}";
//		allCases.add(noOptionCase);
//
//		String noOptionCase = "{\"value\":\"77\",\"thisFilterGroup\":false,\"allSubs\":false,\"asOverride\":true,\"comment\":\"asd\",\"tsId\":1060087,\"clickStatusId\":23}";
//		allCases.add(noOptionCase);
//
//		String noOptionCase = "{\"value\":\"77\",\"thisFilterGroup\":false,\"allSubs\":false,\"asOverride\":true,\"comment\":\"asd\",\"tsId\":1060087,\"clickStatusId\":23}";
//		allCases.add(noOptionCase);
		return allCases;
	}

	private static void setTheHeaders(HttpPost post) {

		post.setHeader("Accept", "*/*");
		post.setHeader("Accept-Encoding", "gzip,deflate,sdch");
		post.setHeader("Accept-Language", "en-US,en;q=0.8");
		post.setHeader("Connection", "keep-alive");
		// post.setHeader("Content-Length", "122");
		post.setHeader("Content-Type", "application/json");
		post.setHeader(
				"Cookie",
				"SnapABugVisit=93b82794-2c56-4e36-8c29-0930a0e29e7d-131683508632704; SnapABugRef=https%3A%2F%2Flocalhost%3A8443%2Flogin%2Flogin.html%3Faction%3Dfail%26amp_target_url%3Dhttps%253A%252F%252Flocalhost%253A8443%252Famp_manager%252Ffilters_management_3d.html%26login_unique%3D2945638056451560968%20; SnapABugHistory=51#; AMP_AUTHENTICATION_TOKEN=12bba77ca7e10d3b10c476e07613bed273c145ad3b2e3fb6d3818b44e015ad94; showmessageoverlay=0; 3dshowmessageoverlay=0; AMP_EXPIRATION_TIME=1389735545000");
		post.setHeader("Host", "localhost:8443");
		post.setHeader("Origin", "https://localhost:8443");
		post.setHeader(
				"Referer",
				"https://localhost:8443/amp_manager/filters_management_3d.html?amp_authentication_token=12bba77ca7e10d3b10c476e07613bed273c145ad3b2e3fb6d3818b44e015ad94");
		post.setHeader(
				"User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
		post.setHeader("X-Requested-With", "XMLHttpRequest");
		// TODO Auto-generated method stub

	}
}

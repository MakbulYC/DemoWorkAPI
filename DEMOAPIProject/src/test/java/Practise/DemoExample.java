package Practise;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.codehaus.plexus.util.IOUtil;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;


public class DemoExample {
	
	String url = "http://ergast.com/api/f1/2017/circuits.xml";
	
//	@Test(priority = 1)
	public void DemoSOAP() throws Exception {
		
		FileInputStream fileinputstream = new FileInputStream("../B2B/PractiseFiles/DemoPractise1.xml");

		RestAssured.baseURI="http://webservices.oorsprong.org/websamples.countryinfo"; // Add here base url
		
		 Response response =
				given()
					.header("Content-Type", "text/xml")
					.and()
					.body(IOUtil.toString(fileinputstream, "UTF-8"))
				.when()
					.post("CountryInfoService.wso?WSDL") 
				.then()
					.statusCode(200)
					.and()
					.log().all()
					.body("soap:Envelope/soap:Body/m:FullCountryInfoResponse/m:FullCountryInfoResult/m:sName.text()", Matchers.notNullValue()).extract().response();
	 
		 	String response1 = response.xmlPath().getString("soap:Envelope/soap:Body/m:FullCountryInfoResponse/m:FullCountryInfoResult/m:sISOCode.text()");
		 	System.out.println("Responce 1 :- " + response1);
	    }
		 

	@Test(priority = 2)
	public void DemoSOAP5() throws Exception {
		
		FileInputStream fileinputstream = new FileInputStream("../B2B/PractiseFiles/DemoPractise1.xml");

		RestAssured.baseURI="http://webservices.oorsprong.org/websamples.countryinfo"; // Add here base url
		
		Response response =
				given()
					.header("Content-Type", "text/xml")
					.and()
					.body(IOUtil.toString(fileinputstream, "UTF-8"))
				.when()
					.post("CountryInfoService.wso?WSDL") 
				.then()
					.assertThat()
					.body("soap:Envelope/soap:Body/m:ListOfLanguagesByCodeResponse/m:ListOfLanguagesByCodeResult/m:tLanguage/m:sISOCode[1].text()", Matchers.notNullValue())
					.extract().response();
		
		 XmlPath xmlpath = new XmlPath(response.asString());
		 String countryCode = xmlpath.getString("ListOfLanguagesByCode");
		 System.out.println("Country Code :- " + countryCode );
	}

//	@Test(priority = 3)
	public void DemoSOAP6() throws Exception {
		
	
		FileInputStream fileinputstream = new FileInputStream("../B2B/PractiseFiles/DemoPractise1.xml");

		RestAssured.baseURI="http://webservices.oorsprong.org/websamples.countryinfo"; // Add here base url
		
		Response response =
				given()
					.header("Content-Type", "text/xml")
					.and()
					.body(IOUtil.toString(fileinputstream, "UTF-8"))
				.when()
					.post("CountryInfoService.wso?WSDL") 
				.then()
					.assertThat()
					.body("soap:Envelope/soap:Body/m:FullCountryInfoAllCountriesResponse/m:FullCountryInfoAllCountriesResult/m:tCountryInfo/m:sISOCode[1].text()", Matchers.notNullValue())
					.body("soap:Envelope/soap:Body/m:FullCountryInfoAllCountriesResponse/m:FullCountryInfoAllCountriesResult/m:tCountryInfo/m:sName[1].text()", Matchers.notNullValue())
					.extract().response();
		
		
		String response1 = response.xmlPath().getString("soap:Envelope/soap:Body/m:FullCountryInfoAllCountriesResponse/m:FullCountryInfoAllCountriesResult/m:tCountryInfo/m:sISOCode[1].text()");
		String response2 = response.xmlPath().getString("soap:Envelope/soap:Body/m:FullCountryInfoAllCountriesResponse/m:FullCountryInfoAllCountriesResult/m:tCountryInfo/m:sName[1].text()");
		
		
		System.out.println("Node Value 1: " + response1);
        System.out.println("Node Value 2: " + response2);
		
		
	}
}

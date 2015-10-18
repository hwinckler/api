package ex.endpoint.login.test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import util.weld.WeldJUnit4Runner;
import ex.endpoint.url.UrlLocator;

@RunWith(WeldJUnit4Runner.class)
public class LoginTest {

	
	private final String LOGIN = "/api/login";
	
	@Inject
	private UrlLocator urlLocator;

	@Test
	public void deveRetonarStatus200ComLoginValido(){
		
		given().
		       formParam("login", "adm").
		       formParam("senha", "adm").
		       when().
		       		post(urlLocator.getUrl() + LOGIN).then().assertThat().statusCode(200);
		
	}
	
	@Test
	public void deveRetonarTokenComLoginValido(){
		
		given().
		       formParam("login", "adm").
		       formParam("senha", "adm").
		       when().
		       		post(urlLocator.getUrl() + LOGIN).then().body("token", notNullValue());
		
	}
	
}

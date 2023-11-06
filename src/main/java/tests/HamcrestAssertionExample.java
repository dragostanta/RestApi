package tests;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class HamcrestAssertionExample {
	
	
	@Test
	public void hamcrestMatchers() {
		
		Response resp = 
				given().
					get("https://swapi.dev/api/planets/1/").
				then().
					extract().
					response();
		
		System.out.println(resp.asString());
		
		JsonPath jsnPath =  resp.jsonPath();
		String name = jsnPath.getString("name");
		System.out.println(name);
		
		//TestNg assert
		assertEquals(name, "Tatooine");
		
		//Hamcrest assert
	//	assertThat(name, "Tatooine");
	//	assertThat(name, "Tatooine");	
		assertThat(name, equalTo("Tatooine"));
		assertThat(name, is("Tatooine")); // is (T value)
		assertThat(name, is(equalTo("Tatooine")));// is(Matcher<T>)
		
		//TestNg assert
		assertNotEquals(name, "Terra");
		
		//Hamcrest assert
		assertThat(name, is(not("Terra")));
		assertThat(name, is(not(equalTo("Terra"))));
		assertThat(name, is(not(instanceOf(Integer.class))));
		assertThat(name, is(instanceOf(String.class)));

		//starts-with
		assertThat(resp.asString(), startsWith("{\"name\""));
		assertThat(name, startsWith("Tato"));
		assertThat(resp.asString(), startsWithIgnoringCase("{\"NAME\""));

		//ends-with
		assertThat(resp.asString(), endsWith("api/planets/1/\"}"));
		assertThat(resp.asString(), endsWithIgnoringCase("aPi/pLaNeTs/1/\"}"));

		//containes
		assertThat(name, containsStringIgnoringCase("tooi"));
		assertThat(resp.asString(), containsString("desert"));
		assertThat(name, containsString("tooi"));
		
		//pattern
		assertThat(name, matchesPattern("[A-Za-z]+"));
		name="Tatooine123";
		assertThat(name, matchesPattern("[A-Za-z0-9]+"));
		String diameter =  jsnPath.getString("diameter");
		assertThat(diameter, matchesPattern("[0-9]+"));

	}
	

}

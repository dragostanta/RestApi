package tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent;

public class SendFromJsonFile  extends BaseComponent{

	
	//read multiple json objects
	//@Test
	public void testJsonFile() throws FileNotFoundException, IOException, ParseException {
		
		
		JSONParser parser =  new JSONParser();
	//	Object obj = parser.parse(new FileReader("data2.json"));
		JSONArray todoList = (JSONArray) parser.parse(new FileReader("data2.json"));;
		
		for(Object todo : todoList) {
			JSONObject objTodo = (JSONObject)todo;
			Response response = doPostRequest("/api/save", objTodo.toJSONString(), 200);
			System.out.println(response.asPrettyString());
			
		}
				
	}
	
	@Test
	public void parseJson() throws IOException, ParseException {
		
		//1.trebuie sa facem un obiect de tip parser pentru JSON
		JSONParser parser =  new JSONParser();
		//2. sa incarc fisierul pe care vreau sa il parsez
		FileReader file =  new FileReader("data3.json");
		//3. parsez fisierul incarcat la pasul anterior
		Object obj = parser.parse(file);
		//4. punem continutul fisierul intr-un JSON array
		JSONArray employeeList = (JSONArray) obj;
		System.out.println(employeeList);
		System.out.println(employeeList.get(0));
		System.out.println(employeeList.get(1));
		//5. vreau sa iau un camp individual din objectul json
		JSONObject employeeObject = (JSONObject) employeeList.get(0);
		System.out.println(employeeObject);
		JSONObject employeeAtribute = (JSONObject) employeeObject.get("employee");
		System.out.println(employeeAtribute);
		System.out.println(employeeAtribute.get("firstName"));
		
		System.out.println("------------------------------------");
		
		FileReader jsonFile = new FileReader("data3.json");
		JsonPath jsonPath = JsonPath.from(jsonFile);
		
		System.out.println(jsonPath.getString("[1].employee.company.apple"));
		
	}
	
	
	
	
}

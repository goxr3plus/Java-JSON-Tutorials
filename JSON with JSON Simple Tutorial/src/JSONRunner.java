import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

/**
 * [[SuppressWarningsSpartan]]
 */
public class JSONRunner {
	
	/**
	 * Constructor
	 */
	public JSONRunner() {
		
		//Create the JSON File
		System.out.println("=========Creating JSon File...=========\n");
		createJSON();
		System.out.println("=========Created...=========");
		
		//Read the JSON File
		System.out.println("\n=========Reading JSon File...=========\n");
		readJSON();
		System.out.println("\n=========Finished Reading JSon File...=========\n");
		
		//Finnaly
		System.out.println("Application finished...");
	}
	
	/**
	 * Read the created JSON File
	 */
	public void readJSON() {
		
		String jsonFilePath = "runner.json"; //The Path of the JSon File
		
		//We are using a File Reader 
		try (FileReader fileReader = new FileReader(jsonFilePath)) {
			
			//Deserialize/Parse the JSON File and get the Root Object
			JsonObject json = (JsonObject) Jsoner.deserialize(fileReader);
			
			//== Get name and Last Name as Strings
			String name = (String) json.get("name");
			String lastName = (String) json.get("lastname");
			
			//Print them 
			System.out.println("Name is : " + name);
			System.out.println("Last name is: " + lastName);
			
			//== Get Toys array
			JsonArray toys = (JsonArray) json.get("toys");
			
			//Print the colors for each toy
			System.out.println("\nToys are :");
			toys.forEach(toy -> {
				JsonObject toyObject = (JsonObject) toy;
				System.out.println("Toy Name :" + toyObject.getString("name") + " , Color : " + toyObject.get("color"));
			});
			
			//== Get children array
			JsonArray children = (JsonArray) json.get("children");
			
			//Print the colors for each toy
			System.out.println("\nChildren are :");
			children.forEach(childrenName -> System.out.println(childrenName));
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * Create our JSON File
	 */
	public void createJSON() {
		
		String jsonFilePath = "runner.json"; //The Path of the JSon File
		
		//===== Root
		JsonObject json = new JsonObject();
		
		//====== Create some objects here		
		json.put("name", "Alexander"); //Add the object "name" to the root
		json.put("lastname", "GOXR3PLUS"); //Add the object "lastname" to the root
		
		//====== Toys Array containing objects
		JsonArray toys = new JsonArray();
		
		JsonObject toyA = new JsonObject(); //toy 1 color
		toyA.put("name", "crocodile");
		toyA.put("color", "yellow");
		JsonObject toyB = new JsonObject(); //toy 2 color
		toyB.put("name", "monkey");
		toyB.put("color", "white");
		
		toys.add(toyA); //add Object toy a to the Toys Array
		toys.add(toyB); //add Object toy b to the Toys Array
		
		json.put("toys", toys); //Add the toys array to the root
		
		//===== Children Array
		JsonArray children = new JsonArray(Arrays.asList("Sofi", "Nick", "John"));
		
		json.put("children", children); //Add the children array to the root
		
		//Write to File	
		try (FileWriter file = new FileWriter(jsonFilePath)) {
			file.write(Jsoner.prettyPrint(json.toJson()));
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new JSONRunner();
	}
	
}

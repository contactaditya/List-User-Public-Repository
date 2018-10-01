import java.net.*;
import java.io.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

   public class ListGithubUserRepositories {  
	   
    /**
     * This is a function to return all the public repositories of the user.
     */ 
     private static String getUserRepositories(String userName) { 
       StringBuffer content = new StringBuffer();
       try{
	 // Create a URL Object
	 URL url = new URL("https://api.github.com/users/" + URLEncoder.encode(userName, "UTF-8") + "/repos");
	 // Create a URLConnection object
	 URLConnection urlConnection = url.openConnection();
	 // Wrap the URLConnection in a Bufferedreader
	 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	 String line;
	 // Read From The URLConnection via the Bufferedreader
	 while ((line = bufferedReader.readLine()) != null) {
	   content.append(line + "\n");
	 }
	 bufferedReader.close();
       } catch(Exception e) {
	 System.out.println();  
	 System.out.println("The github username does not exist. Please enter a github username that currently exists on github.com");
	 System.out.println();  
	 e.printStackTrace();
       }
       return content.toString(); 
     }
		
     public static void main(String[] args) throws ParseException {    	 	
       String userName = new String();	  
       Scanner input = new Scanner(System.in);
       System.out.print("Enter the username whose public repositories you want to list: ");
       userName = input.nextLine();   
       String result = getUserRepositories(userName);
       System.out.println();
       List<String> userRepository = new ArrayList<String>();
       JSONParser jsonParser = new JSONParser();   
       Object object = jsonParser.parse(result);
       JSONArray arrayObject = (JSONArray) object;
       for (Object object1 : arrayObject) {
         JSONObject jsonObject = (JSONObject) object1;  
         String name = (String) jsonObject.get("name");  
	 userRepository.add(name);
       }
       System.out.print("The names of public repositories that are accessible to the user account " + userName  + " is: " + userRepository);
       input.close();
     }
   }

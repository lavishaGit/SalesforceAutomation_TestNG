package com.salesforce.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtility {
	
	public static void writedatatofile(String path,String key,String value) {
		try (FileOutputStream fs = new FileOutputStream(path)) {
            Properties properties = new Properties();

           
            // Load existing properties (if any)
          try (FileInputStream finput= new FileInputStream(path)) {
                properties.load(finput);
            } catch (IOException e) {
                // File might not exist yet, ignore if it's the case
            }

            // Set the new key-value pair
            properties.setProperty(key, value);

            // Save the updated properties to the file
            properties.store(fs, "write");

            System.out.println("Property '" + key + "' written to " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	

	
	
	public static String readdatatofile(String path,String key) {
        Properties properties = new Properties();

		try (FileInputStream finput= new FileInputStream(path)) {
            properties.load(finput);
        } catch (IOException e) {
            // File might not exist yet, ignore if it's the case
        }
		
		return properties.getProperty(key);
		
		
	}
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
String path="/Users/nitin/eclipseworkspace/Java/JavaProject-TeKArch/resources/writeread.properties";
writedatatofile(path,"id","1234");
String value=readdatatofile(path, "id");
System.out.println("value of 'key' is "+value);
	}
}

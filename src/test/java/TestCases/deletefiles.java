package TestCases;

import java.io.File;

import org.testng.annotations.Test;

public class deletefiles {
	File folder = new File("D:\\Automation\\GoogleTags\\Reports");
	@Test
	public void delete(){

    // Get a list of all files in the folder
    File[] files = folder.listFiles();

    if (files != null) {
        // Iterate through the files and delete each one
        for (File file : files) {
            if (file.isFile()) { // Check if it's a file (not a folder)
               try{file.delete();
               }catch(Exception e)
               {
            	   System.out.println(e);
            	   
               }
               
            }
        }
    } else {
        System.out.println("No files found in the folder.");
    }
	}
}

package totoye;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AsWindowsEXE {
	
	AsWindowsEXE(String name, String folder_path, String mainfile, String icon)
	{
		//Create a JAR of the project
		new AsJava(name, folder_path, mainfile);
		
		//Convert the icon to .ICO
		new FileManage().convertToICO(mainfile, folder_path+"\\game_icon.ico");
		
		//Create a XML file
		File xmlFile = new File(folder_path+"\\config.xml");
		
		if(!xmlFile.exists())
		{
			try {
				xmlFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		FileWriter writer;
		try 
		{
			writer = new FileWriter(xmlFile);
			writer.write("");
			writer.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//convert the project to exe
		
		//Delete the .ico, .xml, and .jar file in the project
	}
}

package totoye;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class Import{
	
	private boolean done = false;
	
	public Import()
	{
		return;
	}
	public Import(UserInterface in, String type)
	{

    	FileExplorer explorer = new FileExplorer("f:", in.window, type);
	    
    	if(explorer.isDone())
    	{
		    File sourcefile = new File(explorer.getFilePath());
		            
		    String asset_name = sourcefile.getName();
		            
		    Path sourcePath = sourcefile.toPath(); 
		    
		    Path targetPath = Paths.get(in.currentProject.get(1)+"\\"+type+"s", asset_name);
	
		    try 
		    {
				Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Asset uploaded!");
			} 
		    catch (IOException e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    done = true;
    	}

	}
	
	public boolean isDone()
	{
		return done;
	}
}


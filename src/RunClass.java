package totoye;

import java.io.IOException;

public class RunClass {
	
	boolean check = false;
	
	public RunClass(String url, String folder){
		// TODO Auto-generated method stub
		try
		{ 
			//The command is the link or path where is located the program
		    // Running the above command
			
			//String command = "javac -d "+folder+"\\classes "+url+"";
			String[]command = {"javac",url};
			
			Runtime run  = Runtime.getRuntime(); 
		    Process proc = run.exec(command);
		    
		    proc.waitFor();
		    
		    int len;
		    
		    if((len = proc.getErrorStream().available()) > 0)
		    {
		    	byte[] buf = new byte[len];
		    	
		    	proc.getErrorStream().read(buf);
		    	
		    	System.err.println(new String(buf));
		    }
		    
		    check = true;
		} 
		catch (IOException | InterruptedException e) 
		{ 
			e.printStackTrace(); 
		}
			 
	}
	
	public boolean get()
	{
		return check;
	}

}


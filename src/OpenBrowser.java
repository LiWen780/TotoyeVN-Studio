package totoye;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class OpenBrowser {

	public OpenBrowser(String url, String from){
		// TODO Auto-generated method stub
		
		try
		{	
			if(from.equals("local"))
			{
				File link = new File(url);
				Desktop.getDesktop().browse(link.toURI());
			}
			else if(from.equals("web"))
			{
				URL link = new URL(url);
				
				Desktop.getDesktop().browse(link.toURI());
			}
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}

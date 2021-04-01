package totoye;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;

import com.sun.javafx.webkit.WebConsoleListener;

@SuppressWarnings("restriction")
public class Runas
{
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double width = screenSize.getWidth();
    private double height = screenSize.getHeight();
    private File fichier2;
    JFXPanel pan;

	
	public Runas(String name, String file_path, JTextPane console, String icon)
	{	
		//nom = name;
		//rootpath = file_path;
		ImageIcon img;
		
		JFrame wind = new JFrame(name.toUpperCase());
		
		if(icon.equals("none"))
		{
			img = new ImageIcon(getClass().getClassLoader().getResource("folderIcon.png"));
		}
		else
		{
			img = new ImageIcon(icon);
		}
		
        
        wind.setIconImage(img.getImage());
		
		pan = new JFXPanel();
		
	   	Platform.runLater(new Runnable() {
				@Override
	        	public void run()
				{
	        		fichier2 = new File(file_path);
					try 
					{
						URL url2 = fichier2.toURI().toURL();
	            		WebEngine engine2;
	            	    WebView wev2=new WebView();
	            	    
	            	    wev2.setMinSize(width, height);
	            	    
	            	    engine2=wev2.getEngine();
	            	    engine2.setJavaScriptEnabled(true);
	            	    
	            	    pan.setScene(new Scene(wev2));
	            	    engine2.load(url2.toString()); 
	            	   
	            	    WebConsoleListener.setDefaultListener((webview, message, lineNumber, sourceId)->{
	            	    	
	            	    	console.setText("Line "+lineNumber+": "+message);
	            	    	System.out.println("Line "+lineNumber+": "+message);
	            	    });
	            	    
					} 
					catch (MalformedURLException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	    
	        	}
	    });
	   	
    	
    	 wind.setExtendedState(JFrame.MAXIMIZED_BOTH);
    	 //wind.setResizable(false);
    	 wind.setMinimumSize(new Dimension(1200,700));
    	 wind.setLocationRelativeTo(null);
		 wind.getContentPane().add(pan);
		 wind.setVisible(true);
		 
		 wind.addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			        if (JOptionPane.showConfirmDialog(wind, 
			            "Close the runner", "Close Window?", 
			            JOptionPane.YES_NO_OPTION,
			            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
			        {
			        	//wind.setVisible(false);
			        	wind.dispose();
			        	//Encrypt the running file
			        	String key = "DarkMilkyWay isn't an antimatter";
			            File inputFile = new File(file_path);
			            File encryptedFile = new File(file_path);
			             
			            try
			            {
			                CryptoUtils.encrypt(key, inputFile, encryptedFile);
			            } 
			            catch (CryptoException ex) 
			            {
			                System.out.println(ex.getMessage());
			                new File(file_path).delete();
			                ex.printStackTrace();
			            }
			            
			        }
			    }
			});

	}
}



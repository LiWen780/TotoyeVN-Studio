package totoye;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

import javax.swing.JTextPane;

import com.sun.javafx.webkit.WebConsoleListener;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

@SuppressWarnings("restriction")
public class GameRunner extends Application{
	
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double width = screenSize.getWidth();
    private double height = screenSize.getHeight();
    private WebView webView;
    private static JTextPane Console;
	
    public GameRunner()
    {
    	
    }
    
    public void lancer(String nom, String file_path, JTextPane console, String icon,String T)
    {
    	Platform.setImplicitExit(false);
    	
    	if(T.equals("false"))
    	{
    		Console = console;
    		Application.launch(this.getClass(), nom, file_path, icon, T);
    	}
    	else
    	{
    		secondLaunch(nom, file_path, icon, console);
    	}
    }
    
    public void firstLaunch(String nom, String file_path, String icon)
	{
		begin(new Stage(), nom, file_path, icon);
		//System.out.println("First launch");
		
		WebConsoleListener.setDefaultListener((webview, message, lineNumber, sourceId)->{
	    	
	    	Console.setText("Line "+lineNumber+": "+message);
	    	//System.out.println("Line "+lineNumber+": "+message);
	    });
	}
    
    public void secondLaunch(String nom, String file_path, String icon, JTextPane console)
    {
        Platform.runLater(new Runnable() {
        	
			@Override
        	public void run()
			{
				begin(new Stage(), nom, file_path, icon);
				
				WebConsoleListener.setDefaultListener((webview, message, lineNumber, sourceId)->{
			    	
			    	console.setText("Line "+lineNumber+": "+message);
			    	//System.out.println("Line "+lineNumber+": "+message);
			    });
			}
        });
	}
	
	@Override
	public void start(Stage primaryStage) 
	{
		Parameters params = getParameters();
		
		if(params != null)
		{
			//first launch
			List<String> list = params.getRaw();
			
			firstLaunch(list.get(0), list.get(1), list.get(2));
		}
	}
	
	public void begin(Stage primaryStage, String name, String file_path, String icon)
	{	
		webView = new WebView();
        webView.getEngine().setJavaScriptEnabled(true);
        webView.setMinSize(2*width/3, 2*height/3);
           
        webView.getEngine().load(new File(file_path).toURI().toString());
     

        StackPane root = new StackPane(webView);
        Scene scene = new Scene(root, 2*width/3, 2*height/3);
        
        //Add Icon
        Image img;
        
		if(icon.equals("none"))
		{	
			try 
			{
				img = new Image(new FileInputStream(getClass().getClassLoader().getResource("folderIcon.png").toURI().toString()));
				primaryStage.getIcons().add(img);
			} 
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			File iconFile = new File(icon);
			
			if(iconFile.exists())
			{
				try 
				{
					img = new Image(new FileInputStream(icon));
					primaryStage.getIcons().add(img);
				} 
				catch (FileNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println("The icon doesn't exist");
			}
				
		}
        //-------------------------------------------------------
		
        primaryStage.setTitle(name.toUpperCase());
        primaryStage.setScene(scene);
        
        primaryStage.sizeToScene();
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				// TODO Auto-generated method stub
				//When the window of the Game Runner is about to close
				webView.getEngine().load(null);
			}
			
		});;
	}
	
	@FXML
	public void exitApplication(ActionEvent event)
	{
		Platform.exit();
		webView.getEngine().load(null);
	}
	
	@Override
	public void stop()
	{
		System.out.println("Stage is closing.");
		webView.getEngine().load(null);
	}

}

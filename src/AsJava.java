package totoye;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class AsJava {
	
	private File desktop;
	private String gameName;
	private String path;
	//private String main;
	private String fileName;
	private File game;
	String folder;
	File dir;
	File classFolder;
	
	String api;
	String license;
	
	AsJava(String name, String folder_path, String mainfile)
	{
		desktop = FileSystemView.getFileSystemView().getHomeDirectory();
		gameName = name;
		path = folder_path;
		dir = new File(folder_path);
		fileName = mainfile.substring(mainfile.lastIndexOf("\\")+1);
		
		
		try 
		{
			api = URLDecoder.decode(new File(getClass().getClassLoader().getResource("API//TotoyeVN_2.1.js").getPath()).getAbsolutePath(),"UTF-8");
			license = URLDecoder.decode(new File(getClass().getClassLoader().getResource("API//LICENSE").getPath()).getAbsolutePath(),"UTF-8");
		} 
		catch (UnsupportedEncodingException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String mainData = "package classes;\nimport javafx.application.Platform;\n import javafx.embed.swing.JFXPanel;\n import javafx.scene.Scene;\nimport javafx.scene.web.WebEngine;\nimport javafx.scene.web.WebView;\nimport java.awt.Dimension;\nimport java.awt.Toolkit;\nimport java.io.File;\nimport java.net.MalformedURLException;\nimport java.net.URL;\nimport javax.swing.JFrame;\npublic class "+name.trim()+"\n{\n private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();\nprivate static double width = screenSize.getWidth();\nprivate static double height = screenSize.getHeight();\nprivate static File fichier2;\nprivate static JFXPanel pan;\nprivate static String rootpath = \""+gameName.trim()+"\\\\"+fileName+"\";\nprivate static String nom = \""+gameName.trim()+"\";\npublic static void main(String[] args)\n{\nJFrame wind = new JFrame(nom.toUpperCase());\npan = new JFXPanel();\nPlatform.runLater(new Runnable() {\n@Override\npublic void run()\n{\nfichier2 = new File(rootpath);\n try\n {\n URL url2 = fichier2.toURI().toURL();\n WebEngine engine2;\n WebView wev2=new WebView();\n wev2.setMinSize(width, height);\n engine2=wev2.getEngine();\nengine2.setJavaScriptEnabled(true);\npan.setScene(new Scene(wev2));\nengine2.load(url2.toString());\n}\n catch (MalformedURLException e) \n{\n e.printStackTrace();\n}\n}\n});\n wind.setExtendedState(JFrame.MAXIMIZED_BOTH);\n wind.setMinimumSize(new Dimension(1200,700));\n wind.getContentPane().add(pan);\n wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n wind.setVisible(true);\n}\n}";
		
		classFolder = new File(desktop+"\\classes");
		
		if(!classFolder.exists())
		{
			classFolder.mkdirs();
		}
		
		game = new File(classFolder,gameName.trim()+".java");
		
		//--------------------Set the API inside the JAR file
		
		File checkAPI = new File(path,"TotoyeVN_2.1.js");
		
		if(!checkAPI.exists())
		{
			 File sourcefile = new File(api);
	         
	         String asset_name = sourcefile.getName();
	         
	         Path sourcePath = sourcefile.toPath();
	         
	         Path targetPath = Paths.get(path, asset_name);
	
	         try 
	         {
				Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
				
				new FileManage().hideFile(checkAPI, true);
	         }
	         catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	         }
		}
        //--------------------Set the license inside the JAR file---------------------------------------
		 File sourcefile2 = new File(license);
         
         String asset_name2 = sourcefile2.getName();
         
         Path sourcePath2 = sourcefile2.toPath();
         
         Path targetPath2 = Paths.get(path, asset_name2);

         try 
         {
			Files.copy(sourcePath2, targetPath2, StandardCopyOption.REPLACE_EXISTING);
         }
         catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
         }
         //----------------------------------------------------------------------
         
		if(!game.exists())
		{
			try
			{
				game.createNewFile();
				
				FileWriter writer = new FileWriter(game);
				writer.write(mainData);
				writer.close();
				
				RunClass we = new RunClass(game.getAbsolutePath(),classFolder.getAbsolutePath());
				
				if(we.get())
				{
					//If the .class are created, let's compress to JAR
					Create();
				}
				
				JOptionPane.showMessageDialog(new JFrame(), "Java game created successfully.");
			}
			catch (IOException e2)
			{
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
	}
	
	public void Create() throws IOException
	{
		  Manifest manifest = new Manifest();
		  manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
		  manifest.getMainAttributes().put(Attributes.Name.CLASS_PATH, ".");
		  manifest.getMainAttributes().putValue("Created-By", "2.0 (TotoyeVN Studio)");
		  manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, "classes."+gameName.trim());
		  JarOutputStream target = new JarOutputStream(new FileOutputStream(desktop+"\\"+gameName.trim()+".jar"), manifest);
		  
		  folder = path.substring(path.lastIndexOf("\\")+1);
		  
		  //delete .java file before compressing to JAR
		  Files.delete(Paths.get(game.getAbsolutePath()));
		  
		  add(new File(path), target, folder, false);
		  add(classFolder, target, "classes", true);
		  
		  target.close();
		  
		  //Delete all the .class and .java files created in the process within the project folder
		  for(File file : dir.listFiles())
		  {
			  if(file.getName().endsWith(".class"))
			  {
				  Files.delete(Paths.get(file.getAbsolutePath()));
			  }
			  else if(file.getName().endsWith(".java"))
			  {
				  Files.delete(Paths.get(file.getAbsolutePath()));
			  }
			  if(file.getName().equals("LICENSE"))
			  {
				  Files.delete(Paths.get(file.getAbsolutePath()));
			  }
		  }
		  new FileManage().delete(classFolder.getAbsolutePath(), "folder");
	}
	
	private void add(File source, JarOutputStream target, String fol, boolean Truth) throws IOException
	{
		BufferedInputStream in = null;
		try
		{
			String fold;
			 
		    if (source.isDirectory())
		    {
		    	String name = fol.replace("\\", "/");
				if (!name.isEmpty())
				{
				    if (!name.endsWith("/"))
				    { 
				    	name += "/";
				    }
				      
				    JarEntry entry = new JarEntry(name);
				    entry.setTime(source.lastModified());
				    target.putNextEntry(entry);
				    target.closeEntry();
				}
					     
				for (File nestedFile: source.listFiles())
				{
				   	fold = nestedFile.getAbsolutePath().substring(nestedFile.getPath().indexOf(fol));
				   	
				   	add(nestedFile, target, fold, false);
				}
			    return;
		    }
	
		    JarEntry entry = new JarEntry(fol.replace("\\", "/"));
		    entry.setTime(source.lastModified());
		    target.putNextEntry(entry);
		    in = new BufferedInputStream(new FileInputStream(source));
	
		    byte[] buffer = new byte[1024];
		    while (true)
		    {
		      int count = in.read(buffer);
		      if (count == -1)
		        break;
		      target.write(buffer, 0, count);
		    }
		    target.closeEntry();
		    
		  }
		  finally
		  {
		    if (in != null)
		      in.close();
		  }
	}
}
